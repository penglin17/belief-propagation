# Triplet-Region-Construction
This project contains the Triplet region construction (TRC) algorithm and a region graph binary factoirzation (RGBF) algorithm.
For usage of the TRC simply input a .uai file and the output will be another .uai file containing TRC regions. 
Users are required to construct the Java application project using our source code BuildTRC_RG.java. 
The input .uai file has the following constriants:
1. all original factors with factor size >3 are binary factored into triplets, i.e., {X Y Z}, and with singleton and pair-wise factors merged into the tripelts;
2. assumming all nodes are ordered such that the triplet {X Y Z} is ordered in a way X is always defined before Y, and Z is the child node of X, Y.

The output .uai file need to be run on another belief propagation platform supporting the format.

The RGBF algorithm is also written in Java, which is used for improving the numerical stability when the absolute region counting numbers are large. The source code for RGBF will be added later. 
