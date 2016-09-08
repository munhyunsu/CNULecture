function root=bisect(a0,b0,ep,max_iterate,index_f)
	if a0 >= b0
		disp('a0 < b0 is not true. Stop!')
		return
	end
	format short
	a = a0; b = b0;
	fa = f(a,index_f); fb = f(b,index_f);
	if sign(fa)*sign(fb) > 0
		disp('f(a0) and f(b0) are of the same sign. Stop!')
		return
	end
	c = (a+b)/2;
	it_count = 0;
	while b-c > ep & it_count < max_iterate
		it_count = it_count + 1;
		fc = f(c,index_f);
		iteration = [it_count a b c fc b-c]
		if sign(fb)*sign(fc) <= 0
			a = c;
			fa = fc;
 		else
 			b = c;
			fb = fc;
		end
 			c = (a+b)/2;
 			pause
	end
	format short
	root = c
	format short
	error_bound = b-c
	format short
	it_count
endfunction
%%%%%%%%%%%%%%%%%%%%%%%%
function value = f(x,index)
% function to define equation for rootfinding problem.
	switch index
		case 1
			value = x.^3 +2*x.^2 - 1;
		case 2
			value = x.^3+x-7;
	end
endfunction
