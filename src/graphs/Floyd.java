package graphs;

public class Floyd {

    public int[][] initSequences(int[][] sequences){
        int n = sequences.length;
        for (int row = 0; row < n; row++) {
              for (int column = 0; column < n; column++) {
                        sequences[row][column] = row;

                }
        }
        return sequences;
    }

    public void sort(int[][] weights){
        int n = weights.length;
        int[][] distances = weights;
        int[][] sequences = initSequences(new int[n][n]);

        for (int k = 0; k < n; k++) {
            print(distances, sequences, k);
            for (int row = 0; row < n; row++) {
                for (int column = 0; column < n; column++) {
                    if ((distances[row][k] + distances[k][column]) < distances[row][column] && row != column && row != k && column != k) {
                        distances[row][column] = distances[row][k] + distances[k][column];
                        sequences[row][column] = k;
                    }
                }
            }
        }
    }

    public void print(int[][] matrix1, int[][] matrix2, int k){
        System.out.println("k: " + k);
        for (int row = 0; row < matrix1.length; row++){
            for (int column = 0; column < matrix1.length; column++){
                System.out.print(matrix1[row][column] + " ");
            }
            System.out.print("  ");
            for (int column = 0; column < matrix1.length; column++){
                System.out.print(matrix2[row][column] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        Floyd floyd = new Floyd();
        int[][] weights = new int[4][4];

        // 1 -- 5 -- 2
        // |  \      |
        // 2    1    3
        // |      \  |
        // 4 -- 4 -- 3

        weights[0][0] = -8;  weights[0][1] =  5; weights[0][2] =  1;  weights[0][3] =  2;
        weights[1][0] =  5;  weights[1][1] = -8; weights[1][2] =  3;  weights[1][3] = -1;
        weights[2][0] =  1;  weights[2][1] =  3; weights[2][2] = -8;  weights[2][3] =  4;
        weights[3][0] =  2;  weights[3][1] = -1; weights[3][2] =  4;  weights[3][3] = -8;

        floyd.sort(weights);

    }
}
