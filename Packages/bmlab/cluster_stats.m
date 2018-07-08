% Constants
TASKS = { 'Rest', 'Rest1', 'Rest2' 'Epso', 'Self', 'Verb' };
TASKS_SHORT = { 'R', 'R1', 'R2' 'M', 'S', 'L' };
SUBJECTS = 1:60;
SUBJECTS([8 15 21 48]) = [];
ALPHA = .05;
STRENGTH_TABLE = csvread('./Datasets/Cluster_Stats/strength_table.csv', 1, 1);

% Compare tasks
table = STRENGTH_TABLE(SUBJECTS, [1 4 6 5]);
main = 'FC Analysis with Cluster Analysis';
fname = './Datasets/Cluster_Stats/cluster_stats_task.png';
stats_task(table, main, fname);

% Compare rests
table = STRENGTH_TABLE(SUBJECTS(44:end), 2:3);
main = 'FC Analysis with Cluster Analysis';
fname = './Datasets/Cluster_Stats/cluster_stats_rest.png';
stats_rest(table, main, fname);
