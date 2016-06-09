function y = simpson(f, a, b, n)
    h = (b-a)/n;
    x = linspace(a, b, n+1);
    fx = feval(f, x);
    y = (h/3)*( f(1) + 4*sum(f(2:2:n-1)) + 2*sum(f(3:2:n-2)) + f(n) );



    f = @(x)sin(x)
