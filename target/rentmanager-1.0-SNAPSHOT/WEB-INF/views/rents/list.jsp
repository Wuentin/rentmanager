<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<%@ include file="/WEB-INF/views/common/header.jsp"%>
		<!-- Left side column. contains the logo and sidebar -->
		<%@ include file="/WEB-INF/views/common/sidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Reservations <a class="btn btn-primary"
						href="${pageContext.request.contextPath}/rents/create">Ajouter</a>
				</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="box">
							<div class="box-body no-padding">
								<table class="table table-striped">
									<tr>
										<th style="width: 10px">#</th>
										<th>Voiture</th>
										<th>Client</th>
										<th>Debut</th>
										<th>Fin</th>
										<th>Action</th>
									</tr>
									<tr>

										<c:forEach items="${reservations}" var="reservation">
<form method="post">
											<input type="text" name="id_client"
												value="${reservation.idClient}">
</form>
											<tr>


												<td>${reservation.id}</td>
												<td>${reservation.idVehicule}</td>
												<td>${reservation.idClient}${clients.nom}cc</td>

												<td>${reservation.dateStart}</td>
												<td>${reservation.dateEnd}</td>

												<td>
													<form method="post">
														<a class="btn btn-primary"
															href="${pageContext.request.contextPath}/rents/details?id=${reservation.id}">
															<i class="fa fa-play"></i>
														</a> <a class="btn btn-success " href="#"> <i
															class="fa fa-edit"></i>
														</a>
														<button type="submit" name="id" value="${reservation.id}"
															class="btn btn-danger">
															<i class="fa fa-trash"></i>
														</button>
													</form>
												</td>
											</tr>
										</c:forEach>
									</tr>
								</table>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col -->
				</div>
			</section>
			<!-- /.content -->
		</div>

		<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@ include file="/WEB-INF/views/common/js_imports.jsp"%>
</body>
</html>
