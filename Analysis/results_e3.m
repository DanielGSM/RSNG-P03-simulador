%1. como cambia con S servidores
%2. como cambia es mejor aumentar número de máquinas o de hilos


%% M
tolerableProbError = 0.05;
tolerableTimeWaiting = 20;
posiblesLambdas = 0.001:0.005:0.3;
elementsInQueue = [0 5 10 15 20 25 30];

for q=1:size(notServeredProbM,3)
    for n=1:size(notServeredProbM,2)
        posM(n,q) = -1;
        maxProbM(n,q) = 0;
        for l=1:size(notServeredProbM,1)
            if (tolerableProbError > notServeredProbM(l,n,q))
                if (maxProbM(n,q) < notServeredProbM(l,n,q))
                    if (tolerableTimeWaiting > avgWaitedTimeM(l,n,q))
                        maxProbM(n,q) = notServeredProbM(l,n,q);
                        posM(n,q) = l;
                        lambdaM(n,q) = posiblesLambdas(l);
                    end
                end
            end
        end
    end
end

for q=1:size(notServeredProbM,3)
    for n=1:size(notServeredProbM,2)
        if(posM(n,q)~=-1)
            waitedTimeM(n,q) = avgWaitedTimeM(posM(n,q),n,q); 
            probQueueM1(n,q) = probQueueM(posM(n,q),n,q);
        else
            waitedTimeM(n,q) = 0; 
            probQueueM1(n,q) = 0;
        end
    end
end

%% D
for q=1:size(notServeredProbD,3)
    for n=1:size(notServeredProbD,2)
        posD(n,q) = -1;
        maxProbD(n,q) = 0;
        for l=1:size(notServeredProbD,1)
            if (tolerableProbError > notServeredProbD(l,n,q))
                if (maxProbD(n,q) < notServeredProbD(l,n,q))
                    if (tolerableTimeWaiting > avgWaitedTimeD(l,n,q))
                        maxProbD(n,q) = notServeredProbD(l,n,q);
                        posD(n,q) = l;
                        lambdaD(n,q) = posiblesLambdas(l);
                    end
                end
            end
        end
    end
end

for q=1:size(notServeredProbD,3)
    for n=1:size(notServeredProbD,2)
        if(posD(n,q)~=-1)
            waitedTimeD(n,q) = avgWaitedTimeD(posD(n,q),n,q); 
            probQueueD1(n,q) = probQueueD(posD(n,q),n,q);
        else
            waitedTimeD(n,q) = 0; 
            probQueueD1(n,q) = 0;
        end
    end
end


%% M
% Prob rechazo VS Lambda
for q=1:size(notServeredProbM,3)
    figure;
    for n=1:size(notServeredProbM,2)
        posiblesRho = posiblesLambdas / (n * 1/60);
        hold all;
        plot(posiblesLambdas,notServeredProbM(:,n,q));
    end
    %xlim([0 3]);
    ylabel('probabilidad de rechazo');
    xlabel('\lambda');
    title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=1','n=2','n=3','n=4','n=5');
    print(strcat('probRechazoVSlambdaM-q=',num2str(elementsInQueue(q))),'-dpng');
end

% Tiempo de espera vs Rho
for q=1:size(notServeredProbM,3)
    figure;
    for n=1:size(notServeredProbM,2)
        posiblesRho = posiblesLambdas / (n * 1/60);
        hold all;
        plot(posiblesRho(1:60),avgWaitedTimeM(1:60,n,q));
    end
   % xlim([0 3]);
    ylabel('tiempo de espera (s)');
    xlabel('\rho');
    title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=1','n=2','n=3','n=4','n=5');
    print(strcat('waitedTimeVSrhoM-q=',num2str(elementsInQueue(q))),'-dpng');
end

% Probabilidad de encolar vs Rho
for q=1:size(notServeredProbM,3)
    figure;
    for n=1:size(notServeredProbM,2)
        posiblesRho = posiblesLambdas / (n * 1/60);
        hold all;
        plot(posiblesRho(1:60),probQueueM(1:60,n,q));
    end
   % xlim([0 3]);
    ylabel('probabilidad de encolar petición');
    xlabel('\rho');
    title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=1','n=2','n=3','n=4','n=5');
    print(strcat('encolarVSrhoM-q=',num2str(elementsInQueue(q))),'-dpng');
end

% Probabilidad de encolar vs lambda
for q=1:size(notServeredProbM,3)
    figure;
    for n=1:size(notServeredProbM,2)
        posiblesRho = posiblesLambdas / (n * 1/60);
        hold all;
        plot(posiblesLambdas(1:60),probQueueM(1:60,n,q));
    end
   % xlim([0 3]);
    ylabel('probabilidad de encolar petición');
    xlabel('\rho');
    title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=1','n=2','n=3','n=4','n=5');
    print(strcat('encolarVSlambdaM-q=',num2str(elementsInQueue(q))),'-dpng');
end


%
for q=1:size(notServeredProbM,3)
    figure;
    for n=1:size(notServeredProbM,2)
        hold all;
        plot(avgWaitedTimeM(:,n,q),probQueueM(:,n,q));
    end
   % xlim([0 3]);
    xlabel('tiempo de espera medio (s)');
    ylabel('probabilidad de encolarse');
    title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=1','n=2','n=3','n=4','n=5');
    print(strcat('tiempoesperaVSprobEncolarM-q=',num2str(elementsInQueue(q))),'-dpng');
end

%% D
% Prob rechazo VS Lambda
for q=1:size(notServeredProbD,3)
    figure;
    for n=1:size(notServeredProbD,2)
        posiblesRho = posiblesLambdas / (n * 1/60);
        hold all;
        plot(posiblesLambdas,notServeredProbD(:,n,q));
    end
    %xlim([0 3]);
    ylabel('probabilidad de rechazo');
    xlabel('\lambda');
    title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=1','n=2','n=3','n=4','n=5');
    print(strcat('graficas-e3/probRechazoVSlambdaD-q=',num2str(elementsInQueue(q))),'-dpng');
end

% Tiempo de espera vs Rho
for q=1:size(notServeredProbD,3)
    figure;
    for n=1:size(notServeredProbD,2)
        posiblesRho = posiblesLambdas / (n * 1/60);
        hold all;
        plot(posiblesRho(1:60),avgWaitedTimeD(1:60,n,q));
    end
   % xlim([0 3]);
    ylabel('tiempo de espera (s)');
    xlabel('\rho');
    title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=1','n=2','n=3','n=4','n=5');
    print(strcat('graficas-e3/waitedTimeVSrhoD-q=',num2str(elementsInQueue(q))),'-dpng');
end

% Probabilidad de encolar vs Rho
for q=1:size(notServeredProbD,3)
    figure;
    for n=1:size(notServeredProbD,2)
        posiblesRho = posiblesLambdas / (n * 1/60);
        hold all;
        plot(posiblesRho(1:60),probQueueD(1:60,n,q));
    end
   % xlim([0 3]);
    ylabel('probabilidad de encolar petición');
    xlabel('\rho');
    title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=1','n=2','n=3','n=4','n=5');
    print(strcat('graficas-e3/encolarVSrhoD-q=',num2str(elementsInQueue(q))),'-dpng');
end

% Probabilidad de encolar vs lambda
for q=1:size(notServeredProbD,3)
    figure;
    for n=1:size(notServeredProbD,2)
        posiblesRho = posiblesLambdas / (n * 1/60);
        hold all;
        plot(posiblesLambdas(1:60),probQueueD(1:60,n,q));
    end
   % xlim([0 3]);
    ylabel('probabilidad de encolar petición');
    xlabel('\rho');
    title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=1','n=2','n=3','n=4','n=5');
    print(strcat('graficas-e3/encolarVSlambdaD-q=',num2str(elementsInQueue(q))),'-dpng');
end


%
for q=1:size(notServeredProbD,3)
    figure;
    for n=1:size(notServeredProbD,2)
        hold all;
        plot(avgWaitedTimeD(:,n,q),probQueueD(:,n,q));
    end
   % xlim([0 3]);
    xlabel('tiempo de espera medio (s)');
    ylabel('probabilidad de encolarse');
    title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=1','n=2','n=3','n=4','n=5');
    print(strcat('graficas-e3/tiempoesperaVSprobEncolarM-q=',num2str(elementsInQueue(q))),'-dpng');
end
