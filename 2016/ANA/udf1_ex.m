c = udf1(86);
fprintf('The F 86 is')
disp(c)

for f = [32: 4: 88]
	c = udf1(f);
	fprintf('The F ')
	fprintf('%d', f)
	fprintf(' is')
	disp(c)
end
