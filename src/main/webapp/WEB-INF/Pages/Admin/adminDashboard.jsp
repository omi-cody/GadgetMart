<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%
    List<Integer> countList = (List<Integer>) request.getAttribute("eachCount");
    List<Integer> ordercountList = (List<Integer>) request.getAttribute("ordereachCount");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/resources/images/system/GadgetMartTab.png">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/stylesheets/admin.css" />

<link rel="stylesheet"
	href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css" />
<!-- Chart.js CDN -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
	<jsp:include page="admin_sidebar.jsp" />

	<div class="main-content">
		<jsp:include page="admin_header.jsp" />

		<main>
			<div class="page-header">
				<h1>GadgetMart Admin Panel</h1>
			</div>

			<div class="page-content">
				<div class="analytics">
					<div class="card">
						<div class="card-head">
							<h2>${orderItemCount}</h2>
							<span class="las la-user-friends"></span>
						</div>
						<div class="card-progress">
							<small>User Orders</small>
						</div>
					</div>
					<div class="card">
						<div class="card-head">
							<h2>${pendingCount}</h2>
							<span class="las la-history"></span>
						</div>
						<div class="card-progress">
							<small>Pending Orders</small>
						</div>
					</div>
					<div class="card">
						<div class="card-head">
							<h2>${deliveredCount}</h2>
							<span class="las la-truck-loading"></span>
						</div>
						<div class="card-progress">
							<small>Order Delivered</small>
						</div>
					</div>
					<div class="card">
						<div class="card-head">
							<h2>${canceledCount}</h2>
							<span class="las la-ban"></span>
						</div>
						<div class="card-progress">
							<small>Order Canceled</small>
						</div>
					</div>
				</div>
			</div>
			<!-- Chart Containers -->
			<div class="charts" style="margin-top: 40px;">
				<div class="doughnutContiner">
					<div class="score-box"><canvas id="scoreChart1"></canvas><p>Apple</p></div>
			        <div class="score-box"><canvas id="scoreChart2"></canvas><p>Nothing</p></div>
			        <div class="score-box"><canvas id="scoreChart3"></canvas><p>Samsung</p></div>
			        <div class="score-box"><canvas id="scoreChart4"></canvas><p>Tablets</p></div>
				</div>
				<div class="barContiner">
					<canvas id="barChart"></canvas>
				</div>
				<div class="pieContiner">
					<canvas id="pieChart"></canvas>
				</div>
			</div>

		</main>
		</div>

		<script>
			// Sample data for charts - replace with actual data from server if needed
			const orderItemCount = ${orderItemCount};
			const pendingCount = ${pendingCount};
			const deliveredCount = ${deliveredCount};
			const canceledCount = ${canceledCount};

			// Bar Chart
			new Chart(document.getElementById('barChart'),
					{
						type : 'bar',
						data : {
							labels : [ 'User Orders', 'Pending Orders',
									'Delivered Orders','canceled Order'],
							datasets : [ {
								label : 'Orders',
								data : [ orderItemCount, pendingCount,
										deliveredCount, canceledCount ],
										backgroundColor: [
											  '#4e73df', 
											  '#f6c23e', 
											  '#1cc88a', 
											  '#36b9cc'  
											]
							} ]
						},
						options : {
							responsive : true,
							plugins : {
								tooltip: { enabled: true },
		                        legend: { display: true },
								title : {
									display : true,
									text : 'Orders Overview'
								}
							}
						}
					});

			// Pie Chart
			new Chart(document.getElementById('pieChart'),
					{
						type : 'pie',
						data : {
							labels : [ 'Apple', 'Nothing','Samsung','Tablets' ],
							datasets : [ {
								data : [  <% for (Integer count : countList) { %>
		                        <%= count %>,
		                        <% } %>],
								backgroundColor : [ '#36b9cc', '#f6c23e',
										'#1cc88a','#00eaff' ]
							} ]
						},
						options : {
							responsive : true,
							plugins : {
								title : {
									display : true,
									text : 'Product Distribution'
								}
							}
						}
					});
			// Circular Score Charts
		// Wait for the DOM to be fully loaded
    document.addEventListener('DOMContentLoaded', function() {
        console.log('DOM content loaded');
        
        // Define scores
        const ordercounts = [<% for (Integer ocount : ordercountList) { %>
        <%= ocount %>,
        <% } %>];
        const totalcounts = ${totalproductOrder};
        
        // Create charts one by one with a slight delay
        createChartWithDelay(0);
        
        function createChartWithDelay(index) {
            if (index >= ordercounts.length) return;
            
            const chartId = 'scoreChart' + (index + 1);
            const order = ordercounts[index];
            const remaining = totalcounts - order;

            
            console.log('Creating chart ' + chartId + ' with score '+ order);
            
            const canvas = document.getElementById(chartId);
            if (!canvas) {
                console.error('Canvas ' + chartId + ' not found');
                return;
            }
            
            // Create chart configuration
            const config = {
                type: 'doughnut',
                data: {
                    datasets: [{
                        data: [order, totalcounts],
                        backgroundColor: ['#00eaff', '#2d2d3f'],
                        cutout: '60	%'
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    plugins: {
                        tooltip: { enabled: true },
                        legend: { display: false },
                        title: {
                            display: true,
                            text: order + '',
                            color: '#fff',
                            position: 'center',
                            font: {
                                size: 20,
                                weight: 'bold'
                            }
                        }
                    }
                }
            };
            
            // Create chart
            try {
                new Chart(canvas, config);
                console.log('Chart ' + chartId + ' created successfully');
                
                // Create next chart with a delay
                setTimeout(function() {
                    createChartWithDelay(index + 1);
                }, 100);
            } catch (error) {
                console.error('Error creating chart ' + chartId + ': ' + error.message);
            }
        }
    });
		</script>
</body>
</html>