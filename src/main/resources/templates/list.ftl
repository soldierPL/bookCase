<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Library</span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.book.id" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="title">Title</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.book.title" id="title"
									   class="username form-control input-sm"
                                       placeholder="Enter book title" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="dateOfPurchase">Date of purchase</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.book.dateOfPurchase" id="dateOfPurchase"
									   class="form-control input-sm" placeholder="yyyy-mm-dd"
                                       required ng-pattern="/^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/"/>
	                        </div>
	                    </div>
	                </div>
	
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="isRead">Read</label>
	                        <div class="col-md-7">
                                    <select ng-model="ctrl.book.isRead" id="isRead">
                                        <option value="Yes">Yes</option>
                                        <option value="No">No</option>
                                    </select>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!ctrl.book.id ? 'Add' : 'Update'}}"
                                   class="btn btn-primary btn-sm"
                                   ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()"
                                    class="btn btn-warning btn-sm">Reset Form</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of books</span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th class="col-md-1">Id</th>
		                <th class="col-md-5">Title</th>
		                <th class="col-md-2">Date of purchase</th>
		                <th class="col-md-2">Is read?</th>
		                <th class="col-md-1"></th>
		                <th class="col-md-1"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="b in ctrl.getAllBooks()">
		                <td>{{b.id}}</td>
		                <td>{{b.title}}</td>
		                <td>{{b.dateOfPurchase}}</td>
		                <td>{{b.isRead}}</td>
		                <td><button type="button" ng-click="ctrl.editBook(b.id)"
                                    class="btn btn-success custom-width">Edit</button></td>
		                <td><button type="button" ng-click="ctrl.removeBook(b.id)"
                                    class="btn btn-danger custom-width">Remove</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
</div>