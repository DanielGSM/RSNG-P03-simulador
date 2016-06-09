% probQueueD
% mask = notServeredProbD<=0.05
% notServeredProbD(mask)
% avgWaitedTimeD
% avgTimeD
% 
% 
% size(notServeredProbD(:,:,1))

%%
tolerableProbError = 0.05;
tolerableTimeWaiting = 20;
posiblesLambdas = 0.0001 : 0.0005 : 0.1001;
elementsInQueue = [0 5 10 15];

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


%%
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
    print(strcat('graficas-e2/probRechazoVSlambdaD-q=',num2str(elementsInQueue(q))),'-dpng');
end

% Tiempo de espera vs Rho
for q=1:size(notServeredProbD,3)
    figure;
    for n=1:size(notServeredProbD,2)
        posiblesRho = posiblesLambdas / (n * 1/60);
        hold all;
        plot(posiblesRho(1:100+n*20),avgWaitedTimeD(1:100+n*20,n,q));
    end
   % xlim([0 3]);
    ylabel('tiempo de espera (s)');
    xlabel('\rho');
    title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=1','n=2','n=3','n=4','n=5');
    print(strcat('graficas-e2/waitedTimeVSrhoD-q=',num2str(elementsInQueue(q))),'-dpng');
end

% Probabilidad de encolar vs Rho
for q=1:size(notServeredProbD,3)
    figure;
    for n=1:size(notServeredProbD,2)
        posiblesRho = posiblesLambdas / (n * 1/60);
        hold all;
        plot(posiblesRho(1:100+n*20),probQueueD(1:100+n*20,n,q));
    end
   % xlim([0 3]);
    ylabel('probabilidad de encolar petición');
    xlabel('\rho');
    title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=1','n=2','n=3','n=4','n=5');
    print(strcat('graficas-e2/encolarVSrhoD-q=',num2str(elementsInQueue(q))),'-dpng');
end

% Probabilidad de encolar vs lambda
for q=1:size(notServeredProbD,3)
    figure;
    for n=1:size(notServeredProbD,2)
        posiblesRho = posiblesLambdas / (n * 1/60);
        hold all;
        plot(posiblesLambdas(1:100+n*20),probQueueD(1:100+n*20,n,q));
    end
   % xlim([0 3]);
    ylabel('probabilidad de encolar petición');
    xlabel('\lambda');
    title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=1','n=2','n=3','n=4','n=5');
    print(strcat('graficas-e2/encolarVSlambdaD-q=',num2str(elementsInQueue(q))),'-dpng');
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
    print(strcat('graficas-e2/tiempoesperaVSprobEncolarM-q=',num2str(elementsInQueue(q))),'-dpng');
end
