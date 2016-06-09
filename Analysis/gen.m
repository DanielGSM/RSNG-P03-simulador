function [  ] = gen( nombre_fichero, dist_t_servicio, lambda, t_servicio, num_elementos )
%GEN Summary of this function goes here
%   Detailed explanation goes here

f = fopen(nombre_fichero, 'w');

t_llegada = 0;
aux = 1/lambda;
for i = 0:num_elementos-1
    t_llegada = t_llegada + exprnd(aux);
    if strcmpi(dist_t_servicio, 'd')
        fprintf(f, '%f %i %f\n', t_llegada, i, t_servicio);
    elseif strcmpi(dist_t_servicio, 'm')
        fprintf(f, '%f %i %f\n', t_llegada, i, exprnd(t_servicio));
    end
end

fclose(f);
end
