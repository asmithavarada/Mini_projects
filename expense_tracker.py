import csv

try:
    import matplotlib.pyplot as plt
    graph_ok = True
except:
    graph_ok = False


file_name = "expenses.csv"

# add expense
from datetime import datetime

def add_expense():

    date = input("Enter date (YYYY-MM-DD): ")

    try:
        entered_date = datetime.strptime(date,"%Y-%m-%d").date()
        today = datetime.today().date()

        if entered_date > today:
            print("Future dates not allowed")
            return

    except:
        print("Invalid date format")
        return


    category = input("Enter category (Food/Travel/Bills): ")
    amount = float(input("Enter amount: "))
    desc = input("Enter description: ")

    with open(file_name,"a",newline="") as f:
        writer = csv.writer(f)
        writer.writerow([date,category,amount,desc])

    print("Expense Added Successfully")


# show summary
def monthly_summary():

    month = input("Enter month (YYYY-MM): ")

    total = 0
    category_data = {}

    try:
        with open(file_name,"r") as f:

            reader = csv.reader(f)

            for row in reader:

                date,category,amount,desc = row

                if date.startswith(month):

                    amount = float(amount)

                    total = total + amount

                    if category in category_data:
                        category_data[category] += amount
                    else:
                        category_data[category] = amount


        print("\nMonthly Expense Summary")
        print("Total Expense =",total)

        print("\nCategory Breakdown")
        for i in category_data:
            print(i,"->",category_data[i])


        # highest spending category
        if category_data:

            high = max(category_data,key=category_data.get)

            print("\nHighest Spending Category:",high)

            # simple insight
            if category_data[high] > 3000:
                print("Suggestion: Try reducing spending on",high)


            # pie chart
            if graph_ok:
                plt.pie(category_data.values(),
                        labels=category_data.keys(),
                        autopct="%1.1f%%")
                plt.title("Expense Distribution")
                plt.show()

            else:
                print("Graph not available")

    except FileNotFoundError:
        print("No expense data found")


# view all expenses
def show_all():

    try:
        with open(file_name,"r") as f:

            reader = csv.reader(f)

            print("\nAll Expenses")
            for row in reader:
                print(row)

    except FileNotFoundError:
        print("No data found")


# menu
while True:

    print("\n--- Smart Expense Tracker ---")
    print("1 Add Expense")
    print("2 Monthly Summary")
    print("3 View All Expenses")
    print("4 Exit")

    choice = input("Enter choice: ")

    if choice=="1":
        add_expense()

    elif choice=="2":
        monthly_summary()

    elif choice=="3":
        show_all()

    elif choice=="4":
        print("Program Closed")
        break

    else:
        print("Invalid Choice")