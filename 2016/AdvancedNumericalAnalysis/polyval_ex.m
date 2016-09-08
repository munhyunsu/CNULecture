a = [1, -2, 2, 1];
x = [-2:0.01:4];
fx = polyval(a, x);

plot(x, fx)
xlabel('x')
ylabel('f(x)')
