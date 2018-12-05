import java.util.*;

public class AstarCube {
	private boolean[][][] cube;
	private MyList openlist;
	private MyList closedlist;
	private int _width;
	private static Pos start;
	private static Pos end;
	private Random _random1;
	private Random _random2;
	private Random _random3;
	
	public static void main(String[] args) {
		AstarCube astar = new AstarCube(10, 100);
		Node node = astar.search(start, end);
		if (node != null) {
			Node cur = node;
			while (!cur._pos.equals(start)) {
				System.out.println(cur._pos._x + " " + cur._pos._y + " " + cur._pos._z);
				cur = cur._parent;
			}
		}
	}

	public AstarCube (int width, int numOfBlock) {
		_width = width;
		cube = new boolean[width][width][width];
		openlist = new MyList();
		closedlist = new MyList();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++) {
				for (int k = 0; k < width; k++) {
					cube[i][j][k] = true;
				}
			}
		}
		_random1 = new Random();
		_random2 = new Random();
		_random3 = new Random();
		int number = numOfBlock;
		while (number > 0) {
			int x = _random1.nextInt(width);
			int y = _random2.nextInt(width);
			int z = _random3.nextInt(width);
			if (cube[x][y][z]) {
				cube[x][y][z] = false;
				number--;
			}
		}
		// for (int i = 0; i < _width; i++) {
		// 	for (int j = 0; j < _width; j++) {
		// 		cube[i][j][5] = false;
		// 	}
		// }
		start = new Pos(0,0,0);
		end = new Pos(_width - 1, _width - 1, _width - 1);
	}

	public Node search (Pos s, Pos f) {
		Node src = new Node(0, 0, 0, s);
		openlist.add(src);
		while (!openlist.isEmpty()) {
			Node q = openlist.poll();
			//System.out.println(" f " + q._f + " g " + q._g + " h " + q._h + " x " + q._pos._x + " y " + q._pos._y + " z " + q._pos._z);
			List<Node> successors = new ArrayList<>();
			// add surrouding cell to the successors list
			for (int i = q._pos._x - 1; i <= q._pos._x + 1; i++) {
				for (int j = q._pos._y - 1; j <= q._pos._y + 1; j++) {
					for (int k = q._pos._z - 1; k <= q._pos._z + 1; k++) {
						//System.out.println(i + " " + j + " " + k);
						if (i >= 0 && i < _width && j >= 0 && j < _width && k >= 0 && k < _width) {
							//System.out.println(i + " " + j + " " + k);
							if (i == q._pos._x && j == q._pos._y && k == q._pos._z) {
								continue;
							}
							if (cube[i][j][k] == false) {
								continue;
							}
							
							Pos successor_pos = new Pos(i, j, k);
							double g = euclidian(q._pos, successor_pos) + q._g;
							double h = euclidian(f, successor_pos);
							Node successor = new Node(g+h, g, h, successor_pos);
							successor._parent = q;
							successors.add(successor);
						}
					}
				}
			}
			for (Node successor : successors) {
				if (successor._pos.equals(f)) {
					return successor;
				}
				if (!closedlist.contains(successor._pos)) {
					if (openlist.contains(successor._pos)) {
						Node node = openlist.get(successor._pos);
						if (node._f < successor._f) {
							continue;
						} else {
							openlist.update(successor);
						}
					} else {
						openlist.add(successor);
					}
				} else {
					continue;
				}
			}
			closedlist.add(q);
		}
		return null;
	}

	private class Node {
		double _f;
		double _g;
		double _h;
		Pos _pos;
		Node _parent;
		public Node(double f, double g, double h, Pos pos) {
			_f = f;
			_g = g;
			_h = h;
			_pos = pos;
			_parent = null;
		}
	}

	private class Pos {
		int _x;
		int _y;
		int _z;
		public Pos (int x, int y, int z) {
			_x = x;
			_y = y;
			_z = z;
		}
		@Override
        public int hashCode() {
            return _x + _y * 31 + _z * 31 * 31;
        }
        
        @Override
        public boolean equals(Object that) {
            return that != null && that instanceof Pos
                && _x == ((Pos)that)._x && _y == ((Pos)that)._y && _z == ((Pos)that)._z;
        }
	}


	private class MyList {
		PriorityQueue<Node> _heap;
		Map<Pos, Node> _map;
		public MyList() {
			_heap = new PriorityQueue<>((o1, o2) -> (int)(o1._f - o2._f));
			_map = new HashMap<>();
		}

		public void add(Node node) {
			_heap.offer(node);
			_map.put(node._pos, node);
		}

		public Node poll() {
			Node node = _heap.poll();
			_map.remove(node._pos);
			return node;
		}

		public void update(Node node) {
			Node nodeToDelete = null;
			for (Node item : _heap) {
				if (item._pos.equals(node._pos)) {
					nodeToDelete = item;
					break;
				}
			}
			_heap.remove(nodeToDelete);
			_map.remove(nodeToDelete._pos);
			_heap.add(node);
			_map.put(node._pos, node);
		}

		public Node get(Pos pos) {
			return _map.get(pos);
		}

		public boolean contains(Pos pos) {
			return _map.containsKey(pos); 
		}

		public boolean isEmpty() {
			return _heap.isEmpty();
		}
	}


		private static double euclidian(Pos p, Pos q) {
			return Math.sqrt(Math.pow((p._x - q._x), 2) + Math.pow((p._y - q._y), 2) + Math.pow((p._z - q._z), 2));
		}
	
		private static double manhattan(Pos p, Pos q) {
			return Math.abs(p._x - q._x) + Math.abs(p._y - q._y) + Math.abs(p._z - q._z);
		}
	
		private static double frenet(Pos p, Pos q) {//TODO: what is Frenet distance?
			return Math.sqrt(Math.pow((p._x - q._x), 2) + Math.pow((p._y - q._y), 2) + Math.pow((p._z - q._z), 2));
		}
}
