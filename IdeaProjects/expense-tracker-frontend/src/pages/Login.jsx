import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import authService from "../services/authService";

function Login() {

    const navigate = useNavigate();

    const [email,setEmail]=useState("");
    const [password,setPassword]=useState("");

    const [loading,setLoading]=useState(false);

    const handleLogin=async(e)=>{

        e.preventDefault();

        try{

            setLoading(true);

            const response=await authService.login(email,password);

            localStorage.setItem("token",response.token);

            alert("Login Successful");

            navigate("/dashboard");

        }catch(error){

            alert(
                error.response?.data?.message ||
                "Login Failed"
            );

        }finally{

            setLoading(false);

        }

    };

    return(

        <div className="container vh-100 d-flex justify-content-center align-items-center">

            <div className="card login-card p-4">

                <div className="text-center mb-4">

                    <div className="logo">
                        Expense Tracker
                    </div>

                    <h5 className="mt-3">
                        Welcome Back 👋
                    </h5>

                </div>

                <form onSubmit={handleLogin}>

                    <div className="mb-3">

                        <label>Email</label>

                        <input
                            type="email"
                            className="form-control"
                            placeholder="Enter Email"
                            value={email}
                            onChange={(e)=>setEmail(e.target.value)}
                            required
                        />

                    </div>

                    <div className="mb-4">

                        <label>Password</label>

                        <input
                            type="password"
                            className="form-control"
                            placeholder="Enter Password"
                            value={password}
                            onChange={(e)=>setPassword(e.target.value)}
                            required
                        />

                    </div>

                    <button
                        className="btn btn-primary"
                        disabled={loading}
                    >

                        {
                            loading
                            ?
                            "Logging in..."
                            :
                            "Login"
                        }

                    </button>

                </form>

                <div className="text-center mt-3">

                    Don't have an account?

                    <Link
                        className="ms-2"
                        to="/register"
                    >
                        Register
                    </Link>

                </div>

            </div>

        </div>

    );

}

export default Login;