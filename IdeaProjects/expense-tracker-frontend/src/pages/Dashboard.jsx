import { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import SummaryCard from "../components/SummaryCard";
import dashboardService from "../services/dashboardService";

function Dashboard() {

    const [summary, setSummary] = useState({
        totalExpenses: 0,
        totalTransactions: 0,
        highestExpense: 0,
        averageExpense: 0
    });

    const [recentExpenses, setRecentExpenses] = useState([]);

    const [loading, setLoading] = useState(true);

    useEffect(() => {
        loadDashboard();
    }, []);

    const loadDashboard = async () => {

        try {

            const summaryData =
                await dashboardService.getSummary();

            const recentData =
                await dashboardService.getRecentExpenses();

            setSummary(summaryData);
            setRecentExpenses(recentData);

        } catch (error) {

            console.log(error);

            alert("Unable to load dashboard");

        } finally {

            setLoading(false);

        }

    };

    if (loading) {

        return (
            <div className="text-center mt-5">
                <h3>Loading Dashboard...</h3>
            </div>
        );

    }

    return (

        <>

            <Navbar />

            <div className="container mt-4">

                <h2 className="mb-4">
                    Dashboard
                </h2>

                <div className="row">

                    <SummaryCard
                        title="Total Expenses"
                        value={"₹ " + summary.totalExpenses}
                    />

                    <SummaryCard
                        title="Transactions"
                        value={summary.totalTransactions}
                    />

                    <SummaryCard
                        title="Highest Expense"
                        value={"₹ " + summary.highestExpense}
                    />

                    <SummaryCard
                        title="Average Expense"
                        value={"₹ " + summary.averageExpense}
                    />

                </div>

                <div className="card shadow mt-4">

                    <div className="card-header bg-primary text-white">

                        <h5 className="mb-0">
                            Recent Expenses
                        </h5>

                    </div>

                    <div className="card-body">

                        {
                            recentExpenses.length === 0 ?

                                <p>No Expenses Found</p>

                                :

                                <table className="table table-hover">

                                    <thead>

                                    <tr>

                                        <th>Title</th>

                                        <th>Category</th>

                                        <th>Amount</th>

                                        <th>Date</th>

                                    </tr>

                                    </thead>

                                    <tbody>

                                    {

                                        recentExpenses.map(expense => (

                                            <tr key={expense.id}>

                                                <td>
                                                    {expense.title}
                                                </td>

                                                <td>
                                                    {expense.category}
                                                </td>

                                                <td>
                                                    ₹ {expense.amount}
                                                </td>

                                                <td>
                                                    {expense.expenseDate}
                                                </td>

                                            </tr>

                                        ))

                                    }

                                    </tbody>

                                </table>

                        }

                    </div>

                </div>

            </div>

        </>

    );

}

export default Dashboard;