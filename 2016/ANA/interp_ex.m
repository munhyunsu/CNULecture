x = [0.5, 1, 1.5, 2.5, 3, 4];
y = [110, 270, 350, 260, 200, 150];


disp('2분 경과 후 전력')
interp1(x, y, 2)
disp('3.5분 경과 후 전력') 
interp1(x, y, 3.5)

