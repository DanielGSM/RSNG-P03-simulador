%clc;
%clear;
tic
datestr(now)
experimentName = 'e3_maxPeticionesMServerMv1';
%rm(experimentName);
[~] = mkdir(experimentName);
[~] = mkdir(strcat(experimentName,'/out'));
% Specific experiment parameters
maxError = 0.05;

% Generator configuracion
typeGen = 'M';
lambdaGen = 0.001:0.005:0.3;
serviceTimeGen = 60;
elementNumGen = 30000;
elementNumOffset = 25000;

% Simulator configuration
numServers = 2;
typeServer = 'rand';
numThreats = (1:2);
elementsInQueue = (0:5:30);

for i = 1:length(lambdaGen)
%     ii = i;
%     %Generator execution
%     generatorPath = '../Generator/gen.py';
%     commandStrGen = strcat({'python '}, generatorPath, {' '}, typeGen, {' '}, ...
%        num2str(lambdaGen(i)),{' '}, num2str(serviceTimeGen),{' '}, num2str(elementNumGen));
%     [status, commandOut] = system(commandStrGen{1});
%     if status==0
    
        fileGenName = strcat(experimentName,'/',typeGen,'-',num2str(lambdaGen(i)),...
            '-', num2str(serviceTimeGen),'-', num2str(elementNumGen));
         gen(fileGenName, typeGen, lambdaGen(i), serviceTimeGen, elementNumGen);
   
%        fGen = fopen(fileGenName,'w');
%        fprintf(fGen, commandOut);
%        fclose(fGen);
%     else
%        error(strcat('Error con: ',fileGenName));
%     end
    mkdir(strcat(experimentName,'/out/lambda',num2str(lambdaGen(i))));
     for j = 1:length(numThreats)
        for k = 1:length(elementsInQueue)
%            disp('i=',i,' j=',j,'k=',k);
            % Simulator execution
            simulatorPath = '../Simulator/dist/Simulator.jar';
            commandStrSim = strcat({'java -jar '}, simulatorPath, {' '}, num2str(numServers), ...
                {' '}, typeServer,{' '}, num2str(numThreats(j)),{' '}, num2str(elementsInQueue(k)),...
                {' '}, fileGenName, {' ./'}, experimentName,{'/out/lambda'},num2str(lambdaGen(i)),'/',...
                {' '}, num2str(elementNumOffset));
            [status, commandOut] = system(commandStrSim{1});
            if status~=0
                error(strcat('Error con: ',commandStrSim));
            end
            
            % Analysis reading
            fileOutName = strcat(experimentName,{'/out/lambda'},num2str(lambdaGen(i)),{'/output '},...
                num2str(numServers),{' '},typeServer,{' '},num2str(numThreats(j)),{' '},...
                num2str(elementsInQueue(k)),{'.txt'});
            results = readFile(fileOutName{1});
            % Petitions by second
            servered = sum([results.servida]==1);
            endTime = max([results.tfin]);
            avgTime(i,j,k) = endTime / servered;
            
            % Not served probability
            totalEntered = size([results.servida],2);
            notServered = sum([results.servida]==0);
            notServeredProb(i,j,k) = notServered / totalEntered;
            
            % Average waiting time
            mask = ([results.tservidor] ~= -1);
            tllegada = [results.tllegada];
            tservidor = [results.tservidor];
            waitedTime = tservidor(mask)-tllegada(mask);
            avgWaitedTime(i,j,k) = mean(waitedTime);
            
            % Probabilidad de que se encole entrando al server
            directPetitions = sum(waitedTime==0);
            queuePetitions = sum(waitedTime~=0);
            probQueue(i,j,k) = queuePetitions / (queuePetitions + directPetitions);
        end
    end
end
toc

%% An�lisis 
%[a,b]=max()



