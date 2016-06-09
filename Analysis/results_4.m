%1. como influye el balanceador


%% RAND
tolerableProbError = 0.05;
tolerableTimeWaiting = 20;
posiblesLambdas = 0.0001:0.002:0.5;
elementsInQueue = 10;

for n=1:size(notServeredProbRAND,2)
    posRAND(n) = -1;
    maxProbRAND(n) = 0;
    for l=1:size(notServeredProbRAND,1)
        if (tolerableProbError > notServeredProbRAND(l,n))
            if (maxProbRAND(n) < notServeredProbRAND(l,n))
                if (tolerableTimeWaiting > avgWaitedTimeRAND(l,n))
                    maxProbRAND(n) = notServeredProbRAND(l,n);
                    posRAND(n) = l;
                    lambdaRAND(n) = posiblesLambdas(l);
                end
            end
        end
    end
end

for n=1:size(notServeredProbRAND,2)
    if(posRAND(n)~=-1)
        waitedTimeRAND(n) = avgWaitedTimeRAND(posRAND(n),n); 
        probQueueRAND1(n) = probQueueRAND(posRAND(n),n);
    else
        waitedTimeRAND(n) = 0; 
        probQueueRAND1(n) = 0;
    end
end

%% RR
for n=1:size(notServeredProbRR,2)
    posRR(n) = -1;
    maxProbRR(n) = 0;
    for l=1:size(notServeredProbRR,1)
        if (tolerableProbError > notServeredProbRR(l,n))
            if (maxProbRR(n) < notServeredProbRR(l,n))
                if (tolerableTimeWaiting > avgWaitedTimeRR(l,n))
                    maxProbRR(n) = notServeredProbRR(l,n);
                    posRR(n) = l;
                    lambdaRR(n) = posiblesLambdas(l);
                end
            end
        end
    end
end


for n=1:size(notServeredProbRR,2)
    if(posRR(n)~=-1)
        waitedTimeRR(n) = avgWaitedTimeRR(posRR(n),n); 
        probQueueRR1(n) = probQueueRR(posRR(n),n);
    else
        waitedTimeRR(n) = 0; 
        probQueueRR1(n) = 0;
    end
end


%% M
% Prob rechazo VS Lambda
    figure;
    for n=2:size(notServeredProbRAND,2)
        posiblesRho = posiblesLambdas / (n * 1/60);
        hold all;
        plot(posiblesLambdas,notServeredProbRAND(:,n));
    end
    %xlim([0 3]);
    ylabel('probabilidad de rechazo');
    xlabel('\lambda');
%    title(strcat('Número hilos =',num2str(elementsInQueue(q))));
    legend('n=2','n=3','n=4','n=5');
    print(strcat('graficas-e4/probRechazoVSlambdaRAND-q='),'-dpng');

% Tiempo de espera vs Rho
    figure;
    for n=2:size(notServeredProbRAND,2)
        posiblesRho = posiblesLambdas / (n * 1/60);
        hold all;
        plot(posiblesRho(1:250),avgWaitedTimeRAND(1:250,n));
    end
   % xlim([0 3]);
    ylabel('tiempo de espera (s)');
    xlabel('\rho');
%    title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=2','n=3','n=4','n=5');
    print(strcat('graficas-e4/waitedTimeVSrhoRAND-q='),'-dpng');


% Probabilidad de encolar vs Rho
    figure;
    for n=2:size(notServeredProbRAND,2)
        posiblesRho = posiblesLambdas / (n * 1/60);
        hold all;
        plot(posiblesRho(1:250),probQueueRAND(1:250,n));
    end
   % xlim([0 3]);
    ylabel('probabilidad de encolar petición');
    xlabel('\rho');
%    title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=2','n=3','n=4','n=5');
    print(strcat('graficas-e4/encolarVSrhoRAND-q='),'-dpng');

% Probabilidad de encolar vs lambda
    figure;
    for n=2:size(notServeredProbRAND,2)
        posiblesRho = posiblesLambdas / (n * 1/60);
        hold all;
        plot(posiblesLambdas(1:250),probQueueRAND(1:250,n));
    end
   % xlim([0 3]);
    ylabel('probabilidad de encolar petición');
    xlabel('\lambda');
%    title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=2','n=3','n=4','n=5');
    print(strcat('graficas-e4/encolarVSlambdaRAND-q='),'-dpng');


%
    figure;
    for n=2:size(notServeredProbRAND,2)
        hold all;
        plot(avgWaitedTimeRAND(:,n),probQueueRAND(:,n));
    end
   % xlim([0 3]);
    xlabel('tiempo de espera medio (s)');
    ylabel('probabilidad de encolarse');
 %   title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=2','n=3','n=4','n=5');
    print(strcat('graficas-e4/tiempoesperaVSprobEncolarRAND-q='),'-dpng');

%% D
% Prob rechazo VS Lambda
    figure;
    for n=2:size(notServeredProbRR,2)
        posiblesRho = posiblesLambdas / (n * 1/60);
        hold all;
        plot(posiblesLambdas,notServeredProbRR(:,n));
    end
    %xlim([0 3]);
    ylabel('probabilidad de rechazo');
    xlabel('\lambda');
   % title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=2','n=3','n=4','n=5');
    print(strcat('graficas-e4/probRechazoVSlambdaRR-q='),'-dpng');

% Tiempo de espera vs Rho
    figure;
    for n=2:size(notServeredProbRR,2)
        posiblesRho = posiblesLambdas / (n * 1/60);
        hold all;
        plot(posiblesRho(1:250),avgWaitedTimeRR(1:250,n));
    end
   % xlim([0 3]);
    ylabel('tiempo de espera (s)');
    xlabel('\rho');
  %  title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=2','n=3','n=4','n=5');
    print(strcat('graficas-e4/waitedTimeVSrhoRR-q='),'-dpng');

% Probabilidad de encolar vs Rho
    figure;
    for n=2:size(notServeredProbRR,2)
        posiblesRho = posiblesLambdas / (n * 1/60);
        hold all;
        plot(posiblesRho(1:250),probQueueRR(1:250,n));
    end
   % xlim([0 3]);
    ylabel('probabilidad de encolar petición');
    xlabel('\rho');
   % title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=2','n=3','n=4','n=5');
    print(strcat('graficas-e4/encolarVSrhoRR-q='),'-dpng');

% Probabilidad de encolar vs lambda
    figure;
    for n=2:size(notServeredProbRR,2)
        posiblesRho = posiblesLambdas / (n * 1/60);
        hold all;
        plot(posiblesLambdas(1:250),probQueueRR(1:250,n));
    end
   % xlim([0 3]);
    ylabel('probabilidad de encolar petición');
    xlabel('\lambda');
   % title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=2','n=3','n=4','n=5');
    print(strcat('graficas-e4/encolarVSlambdaRR-q='),'-dpng');


%
    figure;
    for n=2:size(notServeredProbRR,2)
        hold all;
        plot(avgWaitedTimeRR(:,n),probQueueRR(:,n));
    end
   % xlim([0 3]);
    xlabel('tiempo de espera medio (s)');
    ylabel('probabilidad de encolarse');
    %title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=2','n=3','n=4','n=5');
    print(strcat('graficas-e4/tiempoesperaVSprobEncolarRR-q='),'-dpng');

