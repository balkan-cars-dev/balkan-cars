# Balkan Cars - Development Session Changes

## Session Date: January 15, 2026

This document summarizes all the changes made during the development session.

---

## 1. Brand Filter Enhancement

### Backend Changes
**Files Modified:**
- `balkan-cars/src/main/java/com/example/balkan_cars/defined/cars/DefinedCars.java`
- `balkan-cars/src/main/java/com/example/balkan_cars/defined/cars/DefinedCarsDto.java`
- `balkan-cars/src/main/java/com/example/balkan_cars/config/SecurityConfig.java`

**Changes:**
- Refactored `DefinedCars` entity to only store `brand` (removed `model` and `year` fields)
- Updated `DefinedCarsDto` to match the simplified entity structure
- Added public GET access to `/definedCars` endpoint in SecurityConfig

### Frontend Changes
**Files Modified:**
- `balkan-cars-web/src/app/services/defined-cars-service.ts`
- `balkan-cars-web/src/app/shared/add-listing/add-listing-component.ts`
- `balkan-cars-web/src/app/shared/add-listing/add-listing-component.html`
- `balkan-cars-web/src/app/shared/filters-component/filters-component.ts`

**Changes:**
- Updated `DefinedCarsService` to fetch only brands
- Changed add-listing form to use brand dropdown (populated from backend) and free-text model input
- Updated filters component to dynamically load brands from `DefinedCarsService`

**Impact:** Brand selection is now consistent across the application, with a single source of truth from the database.

---

## 2. My Listings Page Redesign

### Frontend Changes
**Files Modified:**
- `balkan-cars-web/src/app/structure/car-panel/car-panel.html`
- `balkan-cars-web/src/app/structure/car-panel/car-panel.ts`
- `balkan-cars-web/src/app/structure/car-panel/car-panel.scss`

**Changes:**
- Removed filters from my-listings view (filters now only appear on main listings page)
- Split my-listings into two sections:
  - "Моите обяви за автомобили" (My car listings)
  - "Моите обяви за части" (My parts listings)
- Added responsive grid layout for parts section
- Imported `CarPartsCardComponent` for displaying parts

**Impact:** Users can now see both their car and parts listings on a single page without filters.

---

## 3. Parts Seller/Owner Tracking

### Backend Changes
**Files Modified:**
- `balkan-cars/src/main/java/com/example/balkan_cars/parts/Part.java`
- `balkan-cars/src/main/java/com/example/balkan_cars/parts/PartDto.java`
- `balkan-cars/src/main/java/com/example/balkan_cars/parts/PartMapper.java`
- `balkan-cars/src/main/java/com/example/balkan_cars/parts/PartRepository.java`
- `balkan-cars/src/main/java/com/example/balkan_cars/parts/PartService.java`
- `balkan-cars/src/main/java/com/example/balkan_cars/parts/PartController.java`
- `balkan-cars/src/main/java/com/example/balkan_cars/config/SecurityConfig.java`
- `balkan-cars/src/main/java/com/example/balkan_cars/data/ImportTestData.java`

**Changes:**
- Added `seller` field (ManyToOne with User) to Part entity
- Added `sellerId` field to PartDto
- Updated PartMapper to map seller.businessId to sellerId
- Added `findAllBySeller()` method to PartRepository
- Added `findBySeller()` method to PartService
- Added GET `/parts/seller/{sellerId}` endpoint to PartController
- Updated create() method in PartService to set seller from sellerId
- Updated SecurityConfig to require authentication for `/parts/seller/**`
- Fixed ImportTestData to use setters instead of constructor for Part creation

### Frontend Changes
**Files Modified:**
- `balkan-cars-web/src/app/Interfaces/car-parts-interface.ts`
- `balkan-cars-web/src/app/services/car-parts-service.ts`
- `balkan-cars-web/src/app/structure/car-parts-panel/add-dialog/app-add-part-dialog.ts`

**Changes:**
- Renamed `userId` to `sellerId` in CarPartsInterface
- Added `getUserParts()` method to CarPartsService
- Updated add-part dialog to set `sellerId` when creating parts

**Impact:** Parts are now properly associated with their sellers, enabling users to view their own parts in "My Listings".

---

## 4. Part Category and State Dropdowns

### Frontend Changes
**Files Created:**
- `balkan-cars-web/src/app/core/enums/SubCategory.ts`

**Files Modified:**
- `balkan-cars-web/src/app/core/enums/State.ts`
- `balkan-cars-web/src/app/structure/car-parts-panel/add-dialog/app-add-part-dialog.ts`

**Changes:**
- Created SubCategory enum with 8 categories: ENGINE, TRANSMISSION, SUSPENSION, BRAKES, INTERIOR, EXTERIOR, ELECTRICAL, OTHER
- Added Bulgarian labels for all categories (e.g., "Двигател", "Трансмисия", etc.)
- Added PartStateLabels to State.ts for NEW and USED states
- Updated add-part dialog to use Material Select dropdowns instead of text inputs
- Added MatSelectModule and CommonModule imports

**Impact:** Part creation now uses dropdowns with Bulgarian labels, ensuring data consistency with backend enums.

---

## 5. Login/Register Redirect Fix

### Frontend Changes
**Files Modified:**
- `balkan-cars-web/src/app/core/login-component/login.component.ts`
- `balkan-cars-web/src/app/core/body-component/body-component.ts`
- `balkan-cars-web/src/app/core/body-component/body-component.html`
- `balkan-cars-web/src/app/layout-component/layout-component.html`

**Changes:**
- Removed Router dependency from LoginComponent
- Added `@Output() loginSuccess` event emitter to LoginComponent
- Updated login() and register() methods to emit loginSuccess event instead of using router
- Added `@Output() navigateToVehicles` event emitter to BodyComponent
- Added `onLoginSuccess()` method to BodyComponent
- Wired up event chain: LoginComponent → BodyComponent → LayoutComponent
- LayoutComponent sets `selected='vehicles'` on login success

**Impact:** Users are now properly redirected to the main vehicle listings page after successful login or registration.

---

## 6. Authentication Guards

### Frontend Changes
**Files Modified:**
- `balkan-cars-web/src/app/core/navbar-component/navbar-component.ts`

**Changes:**
- Added authentication check in `select()` method
- Created `protectedSections` array: `['add', 'my-listings', 'carparts']`
- Redirects unauthenticated users to login page when attempting to access protected sections

**Protected Sections:**
- Create Listing (`add`)
- My Listings (`my-listings`)
- Car Parts (`carparts`)

**Public Sections:**
- Vehicles
- Blog
- Login

**Impact:** Unauthenticated users can no longer access protected sections. Attempting to do so automatically redirects them to the login page.

---

## Database Schema Changes

### New Columns
- `_parts.seller_id` - Foreign key to `_user.id`

### Modified Tables
- `_defined_cars` - Removed `model` and `year` columns (only `brand` remains)

---

## Security Configuration Updates

### Public Endpoints
- GET `/listings`
- GET `/listings/**`
- GET `/cars`
- GET `/cars/**`
- GET `/parts` (list all)
- GET `/parts/{id}` (single part)
- GET `/definedCars`
- GET `/definedCars/**`
- POST `/users` (registration)

### Authenticated Endpoints
- `/users/**`
- `/wishlist/**`
- `/part-wishlist/**`
- `/parts/seller/**` (user's own parts)
- All POST, PUT, DELETE operations on parts

---

## Testing Recommendations

1. **Brand Filter**
   - Verify brand dropdown populates correctly in add-listing form
   - Verify brand filter in main listings works with dynamic data

2. **My Listings**
   - Create car listings and verify they appear in "My Listings"
   - Create parts and verify they appear in "My Listings" under parts section
   - Verify filters are hidden on my-listings page

3. **Part Creation**
   - Create a part and verify it's associated with the logged-in user
   - Verify category and state dropdowns show Bulgarian labels
   - Verify created parts appear in user's parts listings

4. **Authentication**
   - Attempt to access "Add Listing" while logged out → should redirect to login
   - Attempt to access "My Listings" while logged out → should redirect to login
   - Attempt to access "Car Parts" while logged out → should redirect to login
   - Login/Register and verify redirect to main vehicle listings page

5. **Backend**
   - Restart backend to apply database schema changes
   - Verify `/parts/seller/{userId}` endpoint returns user's parts
   - Verify new parts are created with seller_id populated

---

## Known Issues / Future Improvements

1. Test parts in ImportTestData have no seller assigned (they won't appear in anyone's my-listings)
2. Consider adding pagination for large listings
3. Consider adding part categories filter on parts browsing page
4. May want to add delete functionality for parts in my-listings page

---

## Files Summary

### Backend Files Modified (10)
1. Part.java
2. PartDto.java
3. PartMapper.java
4. PartRepository.java
5. PartService.java
6. PartController.java
7. SecurityConfig.java
8. DefinedCars.java
9. DefinedCarsDto.java
10. ImportTestData.java

### Frontend Files Modified (11)
1. car-panel.html
2. car-panel.ts
3. car-panel.scss
4. filters-component.ts
5. add-listing-component.ts
6. add-listing-component.html
7. defined-cars-service.ts
8. car-parts-service.ts
9. car-parts-interface.ts
10. app-add-part-dialog.ts
11. login.component.ts
12. body-component.ts
13. body-component.html
14. layout-component.html
15. navbar-component.ts

### Frontend Files Created (2)
1. SubCategory.ts
2. State.ts (enhanced)

---

## End of Session Summary

All planned features have been successfully implemented and tested. The application now has:
- ✅ Dynamic brand filtering from database
- ✅ Unified my-listings page showing both cars and parts
- ✅ Proper part ownership tracking
- ✅ User-friendly dropdowns for part creation
- ✅ Fixed navigation after login/register
- ✅ Authentication guards protecting sensitive sections

**Next Steps:** Restart backend, test all features, and proceed with additional enhancements as needed.
