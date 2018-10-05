#!/usr/bin/env bash

qiime phylogeny align-to-tree-mafft-fasttree \
	--i-sequences $CGM/rep-seqs.qza \
	--o-alignment $CGM/aligned-rep-seqs.qza \
	--o-masked-alignment $CGM/masked-aligned-rep-seqs.qza \
	--o-tree $CGM/unrooted-tree.qza \
	--o-rooted-tree $CGM/rooted-tree.qza

qiime tools export \
	--input-path $CGM/unrooted-tree.qza \
	--output-path $CGM

mv $CGM/tree.nwk $CGM/unrooted-tree.nwk

qiime tools export \
	--input-path $CGM/rooted-tree.qza \
	--output-path $CGM

mv $CGM/tree.nwk $CGM/rooted-tree.nwk
