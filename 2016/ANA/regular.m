function root=regular(a0, b0, max_iterate, index_f)
	if a0 >= b0
		disp('a0 < b0 is not true. Stop!')
		return
	end

	format short
	a = a0; b = b0;
	fa = f(a, index_f); fb = f(b, index_f);
	if sign(fa)*sign(fb) > 0
		disp('f(a0) and f(b0) are of the same sign. Stop!')
		return
	end

	for i = 0:max_iterate
		fa = f(a, index_f); fb = f(b, index_f);
		m = (fb - fa)/(b-a);
		x = a - (fa/m);
		fx = f(x, index_f);

		disp([fa, fb, m, x])

		if (sign(fa)*sign(fx) < 0) || (sign(fx)*sign(fb) > 0);
			a = a; b = x;
		elseif (sign(fa)*sign(fx) >= 0) || (sign(fx)*sign(fb) <= 0);
			a = x; b = b;
		endif

		disp([a, b])
	endfor

	root = x;
endfunction

%%%%%%%%%%%%%%%%%%%%%%%%
function value = f(x, index)
% function to define equation for rootfinding problem.
	switch index
		case 1
			value = x.^3 +2*x.^2 - 1;
		case 2
			value = x.^3 + x - 7;
	end
endfunction
