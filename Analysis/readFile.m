function [ out ] = readFile( fileName )
%READFILE Function to read the exit file of simulator
%   fileName: string as name of the file and route if necesary.

    fid = fopen(fileName);
    
    out = struct('tllegada', {}, 'idllegada', {}, 'tservicio', {},...
        'servida', {}, 'tservidor', {}, 'tfin', {});
    
    i = 1;
    tline = fgetl(fid);
    while ischar(tline)
        [token, remain] = strtok(tline, ' ');
        out(i).tllegada = str2double(token);
        [token, remain] = strtok(remain, ' ');
        out(i).idllegada = str2double(token);
        [token, remain] = strtok(remain, ' ');
        out(i).tservicio = str2double(token);
        [token, remain] = strtok(remain, ' ');
        out(i).servida = str2double(token);
        if (out(i).servida==1)
            [token, remain] = strtok(remain, ' ');
            out(i).tservidor = str2double(token);
            out(i).tfin = str2double(remain);
        else
            out(i).tservidor = -1;
            out(i).tfin = -1;
        end
            
        i = i + 1;
        tline = fgetl(fid);
    end

    fclose(fid);

end

