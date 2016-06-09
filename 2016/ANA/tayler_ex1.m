clear

x = [1.0:0.1:3.0];

fx = sqrt(x);
p1 = sqrt(2) * (sqrt(2)/4).*(x-2);
p2 = p1-(sqrt(2)/32)*(x-2).^2;

plot(x, fx, '-r', x, p1, '-g', x, p2, '-.b');

h = legend('fx', 'p1', 'p2', 4);
