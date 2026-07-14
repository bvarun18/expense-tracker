import { Link, useNavigate } from "react-router-dom";

function Navbar() {

    const navigate = useNavigate();

    const logout = () => {

        localStorage.removeItem("token");

        navigate("/");
    };

    return (

        <nav className="navbar navbar-expand-lg navbar-dark bg-primary">

            <div className="container">

                <Link className="navbar-brand fw-bold" to="/dashboard">
                    Expense Tracker
                </Link>

                <div>

                    <Link
                        to="/dashboard"
                        className="btn btn-outline-light me-2"
                    >
                        Dashboard
                    </Link>

                    <Link
                        to="/expenses"
                        className="btn btn-outline-light me-2"
                    >
                        Expenses
                    </Link>

                    <button
                        className="btn btn-danger"
                        onClick={logout}
                    >
                        Logout
                    </button>

                </div>

            </div>

        </nav>

    );
}

export default Navbar;