	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">SIM KOPERASI</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">

					<!-- master dropdown -->
					
					<li class="dropdown">
						<a <c:if test = "${sessionScope.tipePengguna == '1'}">href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" title="Master Data"</c:if>><c:if test = "${sessionScope.tipePengguna == '1'}"><span class="fa fa-th-list" aria-hidden="true">
							</span> Master Data</a></c:if>
						<ul class="dropdown-menu">
							<li><a <c:if test = "${sessionScope.tipePengguna == '1'}"> href="kategori_pinjaman" </c:if>><span class="fa fa-th-list" aria-hidden="true"></span> Kategori Pinjaman</a></li>
							<li><a <c:if test = "${sessionScope.tipePengguna == '1'}">href="pengguna" </c:if>><span class="fa fa-th-list" aria-hidden="true"></span> Pengguna</a></li>
						</ul>
					</li>
					
					<!--ending dropdown-->
					<!--transaction dropdown-->
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" title="Transaksi"><span class="fa fa-th-list" aria-hidden="true"></span> Transaksi</a>
						<ul class="dropdown-menu">
							<li><a href="simpanan"><span class="fa fa-th-list" aria-hidden="true"></span> Simpanan </a></li>
							<li><a href="pinjaman"><span class="fa fa-th-list" aria-hidden="true"></span> Pinjaman </a></li>
						</ul>
					</li>
					<!--ending dropdown-->
					<!--report dropdown-->
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" title="Laporan"><span class="fa fa-th-list" aria-hidden="true"></span> Laporan</a>
						<ul class="dropdown-menu">
							<!-- <li><a href="laporan_pembelian"><span class="fa fa-th-list" aria-hidden="true"></span> Laporan Pembelian </a></li>
							<li><a href="laporan_penjualan"><span class="fa fa-th-list" aria-hidden="true"></span> Laporan Penjualan </a></li>
							<li><a href="laporan_retur"><span class="fa fa-th-list" aria-hidden="true"></span> Laporan Retur </a></li> -->
						</ul>
					</li>
					<!--ending dropdown-->
					<li><a id="logout" href="logout?token=${token}"><span class="fa fa-sign-out"></span> Logout</a></li>
				</ul>


			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>