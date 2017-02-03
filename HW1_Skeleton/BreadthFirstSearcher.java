import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Breadth-First Search (BFS)
 * 
 * You should fill the search() method of this class.
 */
public class BreadthFirstSearcher extends Searcher {

	/**
	 * Calls the parent class constructor.
	 * 
	 * @see Searcher
	 * @param maze initial maze.
	 */
	public BreadthFirstSearcher(Maze maze) {
		super(maze);
	}

	/**
	 * Main breadth first search algorithm.
	 * 
	 * @return true if the search finds a solution, false otherwise.
	 */
	public boolean search() {
		// FILL THIS METHOD

		// explored list is a 2D Boolean array that indicates if a state associated with a given position in the maze has already been explored.
		boolean[][] explored = new boolean[maze.getNoOfRows()][maze.getNoOfCols()];

		// ...

		// Queue implementing the Frontier list
		LinkedList<State> queue = new LinkedList<>();

		while (!queue.isEmpty()) {
			// TODO return true if find a solution
			// TODO maintain the cost, noOfNodesExpanded (a.k.a. noOfNodesExplored),
			// maxDepthSearched, maxSizeOfFrontier during
			// the search
			State s = queue.pop();
			// set this square as explored:
			int exploredx  = s.getX();
			int exploredy = s.getY();
			explored [exploredx][exploredy] = true;

			maze.setOneSquare(s.getSquare(),'*');

			if(s.isGoal(maze)){

				return true;

			} else {

				ArrayList<State> successors = s.getSuccessors(explored,maze);
				for(int i = 0; i< successors.size();i++){
					State successor = successors.get(i);
					queue.add(successor);
				}


				this.maxDepthSearched++;
				this.maxSizeOfFrontier+=successors.size();
				this.noOfNodesExpanded+=successors.size();
				this.cost+=successors.size();

			}
			// TODO update the maze if a solution found

			// use queue.pop() to pop the queue.
			// use queue.add(...) to add elements to queue

		}

		// TODO return false if no solution
		return false;
	}
}
