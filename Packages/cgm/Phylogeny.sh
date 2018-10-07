#!/usr/bin/env bash

qiime phylogeny align-to-tree-mafft-fasttree \
	--i-sequences $DATASETS/rep-seqs.qza \
	--o-alignment $DATASETS/aligned-rep-seqs.qza \
	--o-masked-alignment $DATASETS/masked-aligned-rep-seqs.qza \
	--o-tree $DATASETS/tree/unrooted-tree.qza \
	--o-rooted-tree $DATASETS/tree/rooted-tree.qza

qiime tools export \
	--input-path $DATASETS/tree/unrooted-tree.qza \
	--output-path $DATASETS/tree

mv $DATASETS/tree/tree.nwk $DATASETS/tree/unrooted-tree.nwk

qiime tools export \
	--input-path $DATASETS/tree/rooted-tree.qza \
	--output-path $DATASETS/tree

mv $DATASETS/tree/tree.nwk $DATASETS/tree/rooted-tree.nwk
