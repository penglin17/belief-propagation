# Triplet-Region-Construction
This project contains the Triplet region construction (TRC) algorithm and a region graph binary factoirzation (RGBF) algorithm.
For usage of the TRC simply input a .uai file and the output will be another .uai file containing TRC regions. 
The users are required to construct the Java application project using our source code. 
The input .uai file has the following constriants:
1. all factors are binary factored to triplets, i.e., X Y Z, with single and pair-wise factors merged into tripelts;
2. assumming all nodes are ordered such that the triplet X Y Z is ordered in a way X is always defined before Y, and Z is the child node of X, Y.
3. the output .uai file need to be run on another belief propagation platform supporting the format.
