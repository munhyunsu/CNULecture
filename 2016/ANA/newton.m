function root = newton(x0, error_bd, max_iterate, index_f)
	format short
	error = 1;
	it_count = 0;
	while abs(error) > error_bd & it_count <= max_iterate
		fx = f(x0, index_f);
		dfx = deriv_f(x0, index_f);
		if dfx == 0
			disp('The derivative is zero. Stop')
			return
		end
		x1 = x0 - fx/dfx;
		error = x1 - x0;
		iteration = [it_count x0 fx dfx error]
		x0 = x1;
		it_count = it_count + 1;
	end
	if it_count > max_iterate
		disp('The number of iterates calculated exceeded')
		disp('max_iterate. An accurate root was not')
		disp('calculated.')
	else
		format short
		root = x1;
	end
endfunction

function value = f(x, index)
	switch index
		case 1
			value = x.^3 + 2*x.^2 - 1;
		case 2
			valse = x.^3 + x - 7;
	end
endfunction

function value = deriv_f(x, index)
	switch index
		case 1
			value = 3 * x.^2 + 4*x;
		case 2
			value = 3*x.^2 + 1;
	end
endfunction
