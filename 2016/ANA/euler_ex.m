delta = 0.1*pi;
y(1) = 1;
k = 0;

for t = [delta:delta:4*pi]
	k = k + 1;
	y(k+1) = y(k) + (delta)*cos(t-delta);
	#y(k+1) = y(k) + (delta/2)*(cos(t-delta)+cos(t));
end

ft = [0:delta/10:4*pi];
fy = 1 + sin(ft);
t = [0:delta:4*pi];

plot(t, y, 'o', ft, fy)
xlabel('time')
ylabel('1+cos(t)')
axis([0 4*pi 0 2.5])
