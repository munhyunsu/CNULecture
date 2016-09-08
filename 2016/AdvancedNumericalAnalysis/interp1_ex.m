x = [0.5, 1, 1.5, 2.5, 3, 4];
y = [110, 270, 350, 260, 200, 150];
x_int = [2, 3.5];

y1 = interp1(x, y, x_int);

plot(x, y, ':', x_int, y1, 'o')
xlabel('Time(min)');
ylabel('Power(micro W)');
axis([0.5, 4, 50, 400]);

