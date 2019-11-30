def get_the_input():
	jobs = []
	while(1):
		each_job = []
		each_job.append(int(input("Enter the job number")))
		each_job.append(int(input("Enter the arrival_time")))
		each_job.append(int(input("Enter the burst time")))
		each_job.append(0)
		jobs.append(each_job)
		choice = int(input(("Have another job? 1. Yes 2. No ")))
		if(choice == 2):
			break

	return jobs


def not_included(each_job):
	global ready_queue
	for i in ready_queue:
		if i[0] == each_job[0]:
			return False
	return True


def update_ready_queue(jobs):
	global current_tick
	global ready_queue
	global turn_around_time
	for each_job in jobs:
		if not_included(each_job) and (each_job[1] <= current_tick) and (each_job[3] < each_job[2]):
			ready_queue.append(each_job)
	current_tick += 1
	return ready_queue

def select_job(ready_queue):
	selected_job = []
	for each_job in jobs:
		if(each_job[2] != each_job[3]):
			selected_job = each_job
			break
	
	global turn_around_time
	global current_tick
	for each_job in ready_queue:
		if(each_job[2] != each_job[3]):
			if(each_job[2] - each_job[3] < selected_job[2] - selected_job[3]):
				selected_job = each_job
	if(selected_job != []):
		selected_job[3] += 1
		if ( selected_job[2] == selected_job[3]):
			turn_around_time.append(list((selected_job[0], current_tick - selected_job[1])))
	return selected_job


					

def calculate_waiting_time():
	global turn_around_time
	global jobs
	global waiting_time
	i = 0
	turn_around_time.sort()
	for each_job in jobs:
		waiting_time.append(list((each_job[0],turn_around_time[i][1] - each_job[2])))
		i += 1
	return waiting_time 
	
def calculate_avg_tat():
	global turn_aroun_time
	atat = 0
	for i in turn_around_time:
		atat += i[1]
	print(float(atat))/len(turn_around_time)


def calculate_avg_waiting_time():
        global waiting_time
        atat = 0
        for i in waiting_time:
                atat += i[1]
        print(float(atat))/len(waiting_time)

def update_gant_chart(s):
	global gant_chart
	if(s != []):
		gant_chart.append(s[0])
	else:
		gant_chart.append("*")
	
def move_ahead():
	for each_job in jobs:
		if(each_job[2] != each_job[3]):
			return True
	return False
jobs = get_the_input()
jobs.sort(key = lambda x : x[1])
print(jobs)
turn_around_time = []
waiting_time =[]
ready_queue = []
current_tick = 0
gant_chart = []
while(move_ahead()== True):
	ready_queue =  update_ready_queue(jobs)
	print(ready_queue)
	s = select_job(ready_queue)
	print(s)
	update_gant_chart(s)
jobs.sort()
print("Gant chart")
print(gant_chart)	
turn_around_time.sort()
print(turn_around_time)	
calculate_avg_tat()
waiting_time = calculate_waiting_time()
print(waiting_time)
calculate_avg_waiting_time()
