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
					Utilisateurs <a class="btn btn-primary"
						href="${pageContext.request.contextPath}/users/create">Ajouter</a>
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
										<th>Nom</th>
										<th>Prenom</th>
										<th>Email</th>
										<th>Naissance</th>
									</tr>
									<tr>

										<c:forEach items="${clients}" var="client">

											<tr>
												<td>${client.idClient}</td>
												<td>${client.nom}</td>
												<td>${client.prenom}</td>
												<td>${client.email}</td>
												<td>${client.bornDate}</td>

												<td>
													<form method="post">
														<a class="btn btn-primary"
															href="${pageContext.request.contextPath}/users/details?id=${client.idClient}">
															<i class="fa fa-play"></i>
														</a> <a class="btn btn-success " href="#"> <i
															class="fa fa-edit"></i>



														</a>



														<button type="submit" name="id" value="${client.idClient}"
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
