[x, y] = meshgrid(-4:0.2:4);
z = x.^2-2*y.^2;

meshz(x, y, z);
xlabel('x');
ylabel('y');
zlabel('z');
