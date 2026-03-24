import java.util.Scanner;

public class Main
{
    public interface ListInterface<T>
    {
        void add(T newEntry);
        T getEntry(int position);
        int getLength();
        boolean isEmpty();
        int recursiveMax();
    }

    public static class LList<T> implements ListInterface<T>
    {
        private Node firstNode;
        private int numberOfEntries;

        public LList()
        {
            firstNode = null;
            numberOfEntries = 0;
        }

        public void add(T newEntry)
        {
            Node newNode = new Node(newEntry);

            if (firstNode == null)
            {
                firstNode = newNode;
            }
            else
            {
                Node currentNode = firstNode;

                while (currentNode.next != null)
                {
                    currentNode = currentNode.next;
                }

                currentNode.next = newNode;
            }

            numberOfEntries++;
        }

        public T getEntry(int position)
        {
            Node currentNode = firstNode;

            for (int i = 0; i < position; i++)
            {
                currentNode = currentNode.next;
            }

            return currentNode.data;
        }

        public int getLength()
        {
            return numberOfEntries;
        }

        public boolean isEmpty()
        {
            return numberOfEntries == 0;
        }

        public int recursiveMax()
        {
            if (isEmpty())
                return Integer.MIN_VALUE;

            return recursiveMaxHelper(0, numberOfEntries - 1);
        }

        private int recursiveMaxHelper(int first, int last)
        {
            if (first == last)
            {
                return (Integer) getEntry(first);
            }

            int mid = (first + last) / 2;

            int leftMax = recursiveMaxHelper(first, mid);
            int rightMax = recursiveMaxHelper(mid + 1, last);

            return Math.max(leftMax, rightMax);
        }

        private class Node
        {
            private T data;
            private Node next;

            private Node(T dataPortion)
            {
                data = dataPortion;
                next = null;
            }
        }
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String answer = "y";

        while (answer.equalsIgnoreCase("y"))
        {
            LList<Integer> list = new LList<>();

            System.out.println("Input a list of integers:");
            String line = scanner.nextLine();

            if (!line.trim().isEmpty())
            {
                String[] numbers = line.split(" ");

                for (String num : numbers)
                {
                    list.add(Integer.parseInt(num));
                }

                System.out.println("The maximum value found by recursiveMax is: " + list.recursiveMax());
            }
            else
            {
                System.out.println("The maximum value found by recursiveMax is: nothing");
            }

            System.out.print("Do you want to continue (y/n): ");
            answer = scanner.nextLine();
        }
    }
}
