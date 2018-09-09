## Excluded Subjects
- Subject 8: age > 30
- Subject 15: severe head motion
- Subject 21: severe head motion
- Subject 48: left-handed

## Common Analysis Scripts
- [slover_batch.m](slover_batch.m): display overlay of brain slices.
- [stats_task.m](stats_task.m): compare FC across tasks with ANOVA and post-hoc comparison.
- [stats_rest.m](stats_rest.m): compare FC across rests with paired t test.

## ROI Analysis
- [dmn_masks.m](dmn_masks.m): collect subject-specific DMN T1 mask and apply DMN T1 mask to fMRI template.
- [roi_stats.R](roi_stats.R)/[roi_stats.m](roi_stats.m): statistical analysis of functional connectivity.

## Cluster Analysis

- [c1_masks.m](c1_masks.m): apply T1 mask in subject space to filtered fMRI to focus on the cortex.
- [templates.m](templates.R): generate fMRI template for display in the future.
- [subjects.R](subjects.R): normalize fMRI and save .RData to Subjects folder.
- [dmn_masks.m](dmn_masks.m): collect subject-specific DMN T1 mask and apply DMN T1 mask to fMRI template.
- [cluster_analysis.R](cluster_analysis.R): perform cluster analysis and save DMN cluster to Cluster_Analysis folder.
- [cluster_display.m](cluster_display.m): overlay DMN cluster image with T1 mask.
- [cluster_stats.R](cluster_stats.R)/[cluster_stats.m](cluster_stats.m): statistical analysis of functional connectivity.

## Independent Component Analysis

- [filtered.m](filtered.m): filter MNI-space images for GIFT toolbox and save to Filtered folder.
- [residuals.m](residuals.m): regress task HRF out of filtered images.
- [converted.m](converted.m): collect regressed filtered resting-state images into Converted folder.
- [aal_collect.R](aal_collect.R): collect targeted DMN regions from AAL cerebrum into AAL_DMN folder.
- [mni_collect.m](mni_collect.m): read and combine images from AAL cerebrum and DMN and apply DMN mask to template.
- [group_ica.R](group_ica.R): choose components to achieve the most probable spatial distribution of DMN.
- [dmn_zmaps.R](dmn_zmaps.R): generate z-maps from component images.
- [mni_masks.m](mni_masks.m): apply T1 mask in MNI space to z-maps to focus on the cortex or DMN.
- [component_stats.R](component_stats.R)/[component_stats.m](component_stats.m): statistical analysis of functional connectivity.
