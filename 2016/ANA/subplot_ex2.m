[x, y] = meshgrid(-4:0.2:4);
z = x.^2-2*y.^2;

subplot(221);
mesh(x, y, z);
xlabel('x');
ylabel('y');
zlabel('z');
title('mesh');

subplot(222);
waterfall(x, y, z);
xlabel('x');
ylabel('y');
zlabel('z');
title('waterfall');

subplot(223);
meshc(x, y, z);
xlabel('x');
ylabel('y');
zlabel('z');
title('meshc');

subplot(224);
meshz(x, y, z);
xlabel('x');
ylabel('y');
zlabel('z');
title('meshz');
