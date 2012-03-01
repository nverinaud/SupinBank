<%-- 
    Document   : _transfer_form
    Created on : Mar 1, 2012, 4:45:16 PM
    Author     : nico
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fieldset>
    <legend>Transfer Information</legend>
    
    
    <div class="control-group">
        <label class="control-label">Transfer Type</label>
        <div class="controls">
            <label class="checkbox"><input checked="checked" type="radio" name="transferType" value="internal"/>&nbsp;&nbsp;&nbsp;Internal</label>
            <label class="checkbox"><input type="radio" name="transferType" value="supinbank"/>&nbsp;&nbsp;&nbsp;SupinBank</label>
            <!--<label class="checkbox"><input type="radio" name="transferType" value="external" />&nbsp;&nbsp;&nbsp;External</label>-->
        </div>
    </div>
    
    <div class="control-group">
        <label class="control-label">Transfer from</label>
        <div class="controls">
            <select name="sourceAccountId">
                <c:forEach var="account" items="${accounts}">
                    <option value="${account.id}">${account.name} (${account.balance} Eur)</option>
                </c:forEach>
            </select>
        </div>
    </div>
    
    <div class="control-group">
        <label class="control-label">Transfer to</label>
        <div class="controls">
            <select name="destinationAccountId">
                <c:forEach var="account" items="${accounts}">
                    <option value="${account.id}">${account.name} (${account.balance} Eur)</option>
                </c:forEach>
            </select>
        </div>
    </div>
    
    <div class="control-group">
        <label class="control-label" for="establishementCode">Account Target Name (BBAN)</label>
        <div class="controls">
            <input type="text" maxlength="5" class="input-small" name="establishementCode" id="establishementCode"/>
            <input type="text" maxlength="5" class="input-small" name="branchCode" id="branchCode"/>
            <input type="text" maxlength="11" class="input-large" name="accountNumber" id="accountNumber"/>
            <input type="text" maxlength="2" class="input-mini" name="key" id="key"/>
        </div>
    </div>
    
    <div class="control-group">
        <label class="control-label" for="amount">Amount</label>
        <div class="controls">
            <input type="text" class="input-xlarge" name="amount" id="amount" value="10.0"/> Eur
        </div>
    </div>
    
    <div class="control-group">
        <label class="control-label" for="wording">Description</label>
        <div class="controls">
            <input type="text" class="input-xlarge" name="wording" id="wording" placeholder="Tell me why ??!!"/>
        </div>
    </div>

    <div class="form-actions">
        <input type="submit" class="btn btn-primary" value="Perform Transfer" />
    </div>
</fieldset>
