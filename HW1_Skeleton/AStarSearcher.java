import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * A* algorithm search
 * 
 * You should fill the search() method of this class.
 */
public class AStarSearcher extends Searcher {

	/**
	 * Calls the parent class constructor.
	 * 
	 * @see Searcher
	 * @param maze initial maze.
	 */
	public AStarSearcher(Maze maze) {
		super(maze);
	}

	/**
	 * Main a-star search algorithm.
	 * 
	 * @return true if the search finds a solution, false otherwise.
	 */
	public boolean search() {

		// FILL THIS METHOD

		// explored list is a Boolean array that indicates if a state associated with a given position in the maze has already been explored. 
		boolean[][] explored = new boolean[maze.getNoOfRows()][maze.getNoOfCols()];
		// ...

		PriorityQueue<StateFValuePair> frontier = new PriorityQueue<StateFValuePair>();

		// TODO initialize the root state and add
		// to frontier list
		// ...
		Square startSquare = maze.getPlayerSquare();
		Square goalSquare = maze.getGoalSquare();
		int rootFValue = (int) Math.sqrt(((startSquare.X - goalSquare.X) ^ 2) + ((startSquare.Y - goalSquare.Y) ^ 2));

		State root = new State(startSquare, null, 0, 0);
		StateFValuePair rootPair = new StateFValuePair(root, rootFValue);

		frontier.add(rootPair);

		while (!frontier.isEmpty()) {
			// TODO return true if a solution has been found
			// TODO maintain the cost, noOfNodesExpanded (a.k.a. noOfNodesExplored),
			// maxDepthSearched, maxSizeOfFrontier during
			// the search
			// TODO update the maze if a solution found
			StateFValuePair currentPair = frontier.poll();
			State currentState = currentPair.getState();
			Square currentSquare = currentState.getSquare();

			// Marked current square explored:
			int currentX = currentSquare.X;
			int currentY = currentSquare.Y;
			explored[currentX][currentY] = true;
			maze.setOneSquare(currentSquare,'*');

			if (currentState.isGoal(maze)){
				return true;
			} else {

				ArrayList<State> currentSuccessors = currentState.getSuccessors(explored, maze);
				this.maxDepthSearched++;
				this.cost++;

				for (int j = 0; j < currentSuccessors.size(); j++) {
					State successorState = currentSuccessors.get(j);
					Square successorSquare = successorState.getSquare();
					int successorX = successorSquare.X;
					int successorY = successorSquare.Y;
					if (!explored[successorX][successorY]) {

						int successorfValue = (int) Math.sqrt(((successorSquare.X - goalSquare.X) ^ 2) +
								((successorSquare.Y - goalSquare.Y) ^ 2));
						StateFValuePair successorPair = new StateFValuePair(successorState,successorfValue);
						frontier.add(successorPair);
						this.maxSizeOfFrontier=frontier.size();
						this.noOfNodesExpanded++;


					}


				}

			}

			// use frontier.poll() to extract the minimum stateFValuePair.
			// use frontier.add(...) to add stateFValue pairs
		}

		// TODO return false if no solution
		return false;
	}

}
