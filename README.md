Thank you for your interests of the paper "Improved High Dimensional Discrete Bayesian Network Inference using Triplet Region Construction". This is the online appendix of the paper.

Instructions:



The code_TRC.rar is only used to build our TRC outer regions and to run it please use a simulation environment supporting the .uai format. Please also ensure the simulation environment supporting the CVM algorithm. For instance, the external simulation environment one could use:

https://github.com/aegelfand/GBP

https://github.com/radum2275/merlin

The code_TRCv1.0 contains the Java source code, input files, data and the test models. 
The test models for TRC can be built automatically by constructing a Java application using the source code and the input files. The input file specifies the variables, cardinalities and the factors (already binary factorized) in a BN, with all parent-child node ordering predefined. This means all variables in a triplet factor {X,Y,Z} has been ordered in a way such that X is defined before Y (in the factor list), and Z is the child node. We have listed all input file names in the source code, to run it, simply uncomment a file name. The output will be a .uai file containing the TRC outer regions, which can run on our suggested simulation platform using the CVM algorithm.
For example, put the input file "asia_input.uai" at the root folder of the Java application and uncomment the file name "asia_input" in the source code, the output will be an output file "asia_TRC.uai" generated automatically. The "test models" folder of v1.0 also contains the .uai files for all algorithms compared in the paper.

The current implementation is v1.0 and a small upgrade version v1.1 (that produces fewer outer regions than v1.0). Future updates will be focused on efficiency optimizations. 

The rgbf.rar contains Java pseudocode for the Region Graph Binary Factorization (RGBF) algorithm, which can be used as a reference to build your own RGBF function for a region graph.

The TRC (along with RGBF) algorithm is also integrated into AgenaRisk (BN software for risk analysis and decision making, www.agenarisk.com).

maintenance: linpeng.cs[at]gmail.com
