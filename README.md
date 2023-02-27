# Multi-threading Experiment (November 2022)

This project was part of my Operating Systems course. This project sought to conduct a multi-threading experiment by modelling it as a problem of mice searching for cheese. 

We assume that there is an 8x8 grid. Each cell in the grid contains a box. Only 1 box in the entire 8x8 grid contains cheese, and the rest are empty. The mice are curious about how beneficial it will be to have multiple mice simultaneously searching the grid for the cheese. In other words, they would like to know how much faster the cheese search gets when more mice search the grid. The location of the box that contains the cheese will move along the diagonal of the grid: (0,0), (1,1), ..., (7,7) - such that the experiment has 8 trials.

Each mouse is responsible for searching specific rows of the grid. Each mouse searches a row from left to right. The row searches of the mice do not overlap. It will take 100 milliseconds for a mouse to open the box inside a given cell of the grid in order to determine if there is cheese inside. The search ends when the cheese is found. Two main things are recorded: the amount of time taken to find the cheese, and the number of boxes opened before the cheese is found. There are 3 scenarios devised as follows, with each scenario involving 8 trails:

1) A single mouse is tasked with searching the entire gird for cheese. This single mouse starts at cell (0,0) and searches every row from left to right.

2) Four mice are tasked with searching the grid for cheese. Mouse 1 starts at (0,0) and searches rows 1 and 2. Mouse 2 starts at (2,0) and searches rows 3 and 4. Mouse 3 starts at (4,0) and searches rows 5 and 6. Mouse 4 starts at (6,0) and searches rows 7 and 8. Each mouse searches a row from left to right. 

3) Eight mice are tasked with searching the grid for cheese. Each mouse starts at the beginning of a row, searching left to right. 

In order to implement this in Java: A thread is used to model a single mouse - with a constructor to specify the starting position of the mouse. A class is used to model the box in each cell of the grid - with a field to indicate if the box contains the cheese. At first the shared "boxes opened counter" is unsynchronised while the search time is recorded for all 8 trials for each of the 3 scenarios. Then for the 3rd scenario, the experiment is re-run after protecting the shared "boxes opened counter" with a semaphore in order to synchronise the counter. 

The findings confirm the reasonable expectation that increasing the number of threads decreases the time taken to complete a task. Furthermore, using a semaphore to synchronise access to the shared "boxes opened counter" slightly decreases the time taken to complete the task, as well as the total number of boxes opened. This confirms that synchronisation can offer improvements in efficiency.

