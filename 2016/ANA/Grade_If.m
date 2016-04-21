clc
clear 


x = input('Enter the grade (1-100): ');

if x >= 90
	Grade = 'A';
elseif x >= 80
	Grade = 'B';
elseif x >= 70
	Grade = 'C';
elseif x >= 60
	Grade = 'D';
else
	Grade = 'E';
end

fprintf('The grade is ')
disp(Grade)
