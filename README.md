The code_TRC.rar is only used to build our TRC outer regions and to run it please use a simulation environment supporting the .uai format. Please also ensure the simulation environment supporting the CVM algorithm. For instance, the external simulation environment one could use:

https://github.com/aegelfand/GBP

https://github.com/radum2275/merlin

There are four folders in the code repository including the Java source code, input files, data and the test models. The test models for TRC can be built automatically by constructing a Java application using the source code and the input files. The input file specifies the variables, cardinalities and the factors (already binary factorized) in a BN, with all parent-child node ordering predefined. This means all variables in a triplet factor {X,Y,Z} has been ordered in a way such that X is defined before Y (in the factor list), and Z is the child node. We have listed all input file names in the source code, and to run it, just uncomment a file name. The output will be a .uai file containing the TRC outer regions, which can run using our suggested simulation platform by the CVM command.
For example, put the input file "asia_input.uai" at the root folder of the Java application and uncomment the file name "asia_input" in the source code, the output will be a list of competing regions displayed to the console and an output file "asia_TRC.uai" generated automatically. The "test models" folder also includes the .uai files for all competing algorithms.

The current implementation is v1.0 and is only used for demostration of the TRC algorithm. It ensures all test models in our folder run correctly for the TRC but it is not bug free. However, we are maintaining the code and welcoming any comments.

Region Graph Binary Factorization (RGBF) algorithm and CCCP algorithms are implemented in AgenaRisk so the file cccp+RGBF.java is only used for a reference. 
