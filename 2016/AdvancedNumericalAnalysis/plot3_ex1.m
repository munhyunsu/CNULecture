t = [0:pi/20:15*pi];

x = cos(t);
y = sin(t);

plot3(x, y, t);
xlabel('x');
ylabel('y');
zlabel('z');
grid;
