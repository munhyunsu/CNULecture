t = [0:pi/180:2*pi];

y1 = sin(t);
y2 = cos(t);
y3 = 1+y2;
y4 = 1-y2;

subplot(221);
plot(y1);
axis([0, 360, -2, 2]);
xlabel('Degree');
ylabel('Magnitude');
title('Sine function');

subplot(222);
plot(y2);
axis([0, 360, -2, 2]);
xlabel('Degree');
ylabel('Magnitude');
title('Cosine function');

subplot(223);
plot(y3);
axis([0, 360, -2, 2]);
xlabel('Degree');
ylabel('Magnitude');
title('Addition function');

subplot(224);
plot(y4);
axis([0, 360, -2, 2]);
xlabel('Degree');
ylabel('Magnitude');
title('Subtraction function');

