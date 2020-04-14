This program implements a brute force algorithm that is designed to solve the Borderlands 3 Borderlands Science mini game for puzzle solutions that will yield the highest possible score.

I made this program because I was curious to see if I could and I never stopped to ask if I should. If I did my math correctly, this algorithm a time complexity of O(n^n) which is to say that if it can do it in 14 minutes, you can do it in 30 seconds (those numbers came from the one test I did before I realized just how stupid this idea was).

If you wish to use the program you will need to be able to compile .java files and edit .txt files.

1. To change the graph being used you will need to edit both graph.txt and validColors.txt as follows.

    * If the space is empty enter a 0

    * If the space contains a Fl4k token enter a 1.

    * If the space contains a Zane token enter a 2.

    * If the space contains a Amara token enter a 3.

    * If the space contains a Moze token enter a 4.

     *Note for validColors.txt*
Each line *must* contain 2 numbers, any fewer and the program will break, any more and the extras won't be read.

2. To execute the program do the following:
    1. Compile Driver.java, Graph.java, and Node.java
    2. execute the resulting file, following all prompts.
    
    *Note* After entering the number of spacer blocks that area available the program may appear to be stuck in an infinite loop. This is not the case, it just takes an absurd amount of time to execute (remember that time complexity?). I would suggest going and doing something short yet productive while you wait for it to finish execution.