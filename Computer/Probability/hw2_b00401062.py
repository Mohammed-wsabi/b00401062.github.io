#!/usr/bin/python

import numpy as np
import matplotlib.pyplot as plt
from scipy.stats import binom
from scipy.stats import hypergeom

def distribution_show(n, p, n1, n2):
    ## generate subplots
    figure, (binom_plt, hypergeom_plt) = plt.subplots(2, sharex = True)
    
    ## binomial distribution
    binom_pmf = binom.pmf(np.arange(n + 1), n, p)
    binom_title = "Binomial Distribution: n = %d, p = %.1f" % (n, p)
    binom_plt.bar(np.arange(n + 1), binom_pmf, width = 1)
    binom_plt.set_xlim([0, n + 1])
    binom_plt.set_title(binom_title)
    
    ## hypergeometric distribution
    hypergeom_pmf = hypergeom.pmf(np.arange(n + 1), n1 + n2, n1, n)
    hypergeom_title = "Hypergeometric Distribution: n1 = %d, n2 = %d" % (n1, n2)
    hypergeom_plt.bar(np.arange(n + 1), hypergeom_pmf, width = 1)
    hypergeom_plt.set_xlim([0, n + 1])
    hypergeom_plt.set_title(hypergeom_title)
    
    ## show and save figures
    figure.show()
    figure.savefig("p=%.1f.png" % p)
    
## figure 1
n = 10
for p in np.linspace(0.1, 0.9, num = 9):
    n1 = round(20 * p)
    n2 = round(20 * (1 - p))
    distribution_show(n, p, n1, n2)

## figure 2
n = 100
for p in np.linspace(0.1, 0.9, num = 9):
    n1 = round(1000 * p)
    n2 = round(1000 * (1 - p))
    distribution_show(n, p, n1, n2)
