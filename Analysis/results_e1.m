% probQueueM
% mask = notServeredProbM<=0.05
% notServeredProbM(mask)
% avgWaitedTimeM
% avgTimeM
% 
% 
% size(notServeredProbM(:,:,1))

%%
tolerableProbError = 0.05;
tolerableTimeWaiting = 20;
posiblesLambdas = 0.0001 : 0.0005 : 0.1001;
elementsInQueue = [0 5 10 15];

for q=1:size(notServeredProbM,3)
    for n=1:size(notServeredProbM,2)
        pos(n,q) = -1;
        maxProb(n,q) = 0;
        for l=1:size(notServeredProbM,1)
            if (tolerableProbError > notServeredProbM(l,n,q))
                if (maxProb(n,q) < notServeredProbM(l,n,q))
                    if (tolerableTimeWaiting > avgWaitedTimeM(l,n,q))
                        maxProb(n,q) = notServeredProbM(l,n,q);
                        pos(n,q) = l;
                        lambda(n,q) = posiblesLambdas(l);
                    end
                end
            end
        end
    end
end

for q=1:size(notServeredProbM,3)
    for n=1:size(notServeredProbM,2)
        if(pos(n,q)~=-1)
            waitedTime(n,q) = avgWaitedTimeM(pos(n,q),n,q); 
            probQueue(n,q) = probQueueM(pos(n,q),n,q);
        else
            waitedTime(n,q) = 0; 
            probQueue(n,q) = 0;
        end
    end
end


%%
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
    print(strcat('graficas-e1/probRechazoVSlambdaM-q=',num2str(elementsInQueue(q))),'-dpng');
end

% Tiempo de espera vs Rho
for q=1:size(notServeredProbM,3)
    figure;
    for n=1:size(notServeredProbM,2)
        posiblesRho = posiblesLambdas / (n * 1/60);
        hold all;
        plot(posiblesRho(1:100+n*20),avgWaitedTimeM(1:100+n*20,n,q));
    end
   % xlim([0 3]);
    ylabel('tiempo de espera (s)');
    xlabel('\rho');
    title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=1','n=2','n=3','n=4','n=5');
    print(strcat('graficas-e1/waitedTimeVSrhoM-q=',num2str(elementsInQueue(q))),'-dpng');
end

% Probabilidad de encolar vs Rho
for q=1:size(notServeredProbM,3)
    figure;
    for n=1:size(notServeredProbM,2)
        posiblesRho = posiblesLambdas / (n * 1/60);
        hold all;
        plot(posiblesRho(1:100+n*20),probQueueM(1:100+n*20,n,q));
    end
   % xlim([0 3]);
    ylabel('probabilidad de encolar petición');
    xlabel('\rho');
    title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=1','n=2','n=3','n=4','n=5');
    print(strcat('graficas-e1/encolarVSrhoM-q=',num2str(elementsInQueue(q))),'-dpng');
end

% Probabilidad de encolar vs lambda
for q=1:size(notServeredProbM,3)
    figure;
    for n=1:size(notServeredProbM,2)
        posiblesRho = posiblesLambdas / (n * 1/60);
        hold all;
        plot(posiblesLambdas(1:100+n*20),probQueueM(1:100+n*20,n,q));
    end
   % xlim([0 3]);
    ylabel('probabilidad de encolar petición');
    xlabel('\lambda');
    title(strcat('Cola q=',num2str(elementsInQueue(q))));
    legend('n=1','n=2','n=3','n=4','n=5');
    print(strcat('graficas-e1/encolarVSlambdaM-q=',num2str(elementsInQueue(q))),'-dpng');
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
    print(strcat('graficas-e1/tiempoesperaVSprobEncolarM-q=',num2str(elementsInQueue(q))),'-dpng');
end
