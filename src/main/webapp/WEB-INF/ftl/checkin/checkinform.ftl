<!DOCTYPE html>
<html>
<head>
<#include "../includes.ftl">
    <title>BirdSquare: Check In</title>
    <script type="text/javascript" src="javascript/validation.js"></script>
    <script type="text/javascript" src="javascript/location.js"></script>
    <script type="text/javascript" src="javascript/birdnameautocomplete.js"></script>
</head>

<body>


<div data-role="page" id="checkinform">

    <form id="checkinform_form" name="birdinformation" onsubmit="return validateForm()" action="homesuccess" method="post" data-ajax="false">
    <#include "../headers/header-without-button.ftl">

        <div data-role="content" id="checkinform-content">

            <div class="ui-widget" style="font-family: 'Arial'">

                <label for="location-field" class="ui-accessible">You are at</label>
            <#--here, input name is to be matched up with what's in validation.js and also in the Model file. id is to match with label for-->
                <input type ="text" name="locationName" id="location-field" placeholder="Enter location name" value="${locationName}"/>
                <p></p>

                <p>Enter sighting details</p>
                <label for="birdname-field" class="ui-hidden-accessible"></label>
                <input type="text" name="birdName" id="birdname-field" placeholder="Bird name"/>

                <label for ="numberofbirds-field" class="ui-hidden-accessible"></label>
                <input type="text" name="number" id="numberofbirds-field" placeholder="Number of birds seen"/>

                <label for="comments" class="ui-hidden-accessible"></label>
                <textarea id="comments" name="comments" placeholder="Comments"></textarea>

                <input type="submit" id="submit" value="Submit"/>

                <input type="hidden" name="latitude" value="${latitude}"/>
                <input type="hidden" name="longitude" value="${longitude}"/>

                <input type="hidden" name="fbuid" id="fbuid" />

                <input type="hidden" name="birdNameList" id="birdNameList" value="<#list allbirds as item>${item},</#list>"/>

            </div>
        </div>

    </form>
<#include "../checkin/popup.ftl">
<#include "../footer.ftl">
</div><!-- /page -->

</body>
</html>

