function value = F1(x)
	value = x.^2 + 7*x + 10
endfunction

fzero(@F1, -5)
fzero(@F1, -2)
fzero(@F1, -1)
fzero(@F1, 0)


function value = F2(x)
	value = 2*x + exp(-x) + 2
endfunction

fzero(@f2, -1)
fzero(@f2, -2)

