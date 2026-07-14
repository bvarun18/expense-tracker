function SummaryCard({ title, value }) {

    return (

        <div className="col-md-3 mb-3">

            <div className="card shadow-sm">

                <div className="card-body text-center">

                    <h6 className="text-secondary">
                        {title}
                    </h6>

                    <h3 className="text-primary">
                        {value}
                    </h3>

                </div>

            </div>

        </div>

    );

}

export default SummaryCard;