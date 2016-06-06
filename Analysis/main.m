experimentName = '1-maxPeticiones1Server';
[~] = mkdir(experimentName);

% Generator configuracion
typeGen = 'M';
lambdaGen = 1; % Variable
serviceTimeGen = 60;
elementNumGen = 100;

% Simulator configuration
numServers = 1;
typeServer = 'rand'; 
numThreats = 5; % Variable
elementsInQueue = 10; % Variable

% Generator execution
generatorPath = '../Generator/gen.py';
commandStrGen = strcat({'python '}, generatorPath, {' '}, typeGen, {' '}, ...
    num2str(lambdaGen),{' '}, num2str(serviceTimeGen),{' '}, num2str(elementNumGen));
[status, commandOut] = system(commandStrGen{1});
if status==0
    fileGenName = strcat(experimentName,'/',typeGen,'-',num2str(lambdaGen),...
        '-', num2str(serviceTimeGen),'-', num2str(elementNumGen));
    fGen = fopen(fileGenName,'w');
    fprintf(fGen, commandOut);
    fclose(f);
else
    error(strcat('Error con: ',fileGenName));
end

% Simulator execution
simulatorPath = '../Simulator/dist/Simulator.jar';
commandStrSim = strcat({'java -jar '}, simulatorPath, {' '}, num2str(numServers), ...
    {' '}, typeServer,{' '}, num2str(numThreats),{' '}, num2str(elementsInQueue),...
    {' '}, fileGenName, {' ./'}, experimentName);
[status, commandOut] = system(commandStrSim{1});
if status~=0
    error(strcat('Error con: ',fileSisName));
end

% Analysis reading
fileOutName = strcat(experimentName,{'/output '},num2str(numServers),{' '},...
    typeServer,{' '},num2str(numThreats),{' '},num2str(elementsInQueue),{'.txt'});
results = readFile(fileOutName{1});

% Petitions by second
totalEntered = size([results.servida],2);
endTime = max([results.tfin]);
avgTime = totalEntered / endTime;

% Not served probability
notServered = sum([results.servida]==0);
notServeredProb = notServered / totalEntered;

% Average waiting time
mask = ([results.tservidor] ~= -1);
tllegada = [results.tllegada];
tservidor = [results.tservidor];
avgWaitedTime = mean(tservidor(mask)-tllegada(mask));