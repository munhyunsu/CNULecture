[x, y] = meshgrid(-4:0.2:4);
z = x.^2-2*y.^2;

subplot(211);
mesh(x, y, z);
xlabel('x');
ylabel('y');
zlabel('z');
title('mesh');

subplot(212);
contour(x, y, z);
xlabel('x');
ylabel('y');
title('contour');
