/*!
 * @license
 * Copyright 2016 Alfresco Software, Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import { Component } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DiagramTooltipComponent } from './diagram-tooltip.component';

@Component({
    template: `
        <div id="diagram-element-id">Hover me</div>
        <diagram-tooltip [data]="data"></diagram-tooltip>`
})
class TestHostComponent {
    data = {
        id: 'diagram-element-id'
    };
}

describe('DiagramTooltipComponent', () => {

    describe('Template', () => {

        let fixture: ComponentFixture<DiagramTooltipComponent>;
        let component: DiagramTooltipComponent;
        let data;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                declarations: [ DiagramTooltipComponent ]
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DiagramTooltipComponent);
            component = fixture.componentInstance;
            data = {
                type: 'awesome-diagram-element',
                name: 'diagram-element-name',
                id: 'diagram-element-id',
                properties: []
            };
            component.data = data;
            fixture.detectChanges();
        });

        it('should render with type and name if name is defined', () => {
            let tooltipHeader = fixture.debugElement.query(By.css('.adf-diagram-tooltip-header'));

            expect(tooltipHeader.nativeElement.innerText).toBe('awesome-diagram-element diagram-element-name');
        });

        it('should render with type and id if name is NOT defined', () => {
            data.name = '';
            fixture.detectChanges();

            let tooltipHeader = fixture.debugElement.query(By.css('.adf-diagram-tooltip-header'));

            expect(tooltipHeader.nativeElement.innerText).toBe('awesome-diagram-element diagram-element-id');
        });

        it('should render the name if name is defined in the tooltip body', () => {
            let nameProperty = fixture.debugElement.query(By.css('.adf-diagram-name-property'));

            expect(nameProperty).not.toBeNull();
            expect(nameProperty.nativeElement.innerText).toBe('Name: diagram-element-name');
        });

        it('should NOT render the name if name is NOT defined in the tooltip body', () => {
            data.name = '';
            fixture.detectChanges();

            let nameProperty = fixture.debugElement.query(By.css('.adf-diagram-name-property'));

            expect(nameProperty).toBeNull();
        });

        it('should render the properties, if there is any', () => {
            data.properties = [
                { name: 'property-1-name', value: 'property-1-value' },
                { name: 'property-2-name', value: 'property-2-value' }
            ];
            fixture.detectChanges();

            let propertyNames = fixture.debugElement.queryAll(By.css('.adf-diagram-general-property > .adf-diagram-propertyName')),
                propertyValues = fixture.debugElement.queryAll(By.css('.adf-diagram-general-property > .adf-diagram-propertyValue'));

            expect(propertyNames.length).toBe(2);
            expect(propertyValues.length).toBe(2);
            expect(propertyNames[0].nativeElement.innerText).toBe('property-1-name:');
            expect(propertyNames[1].nativeElement.innerText).toBe('property-2-name:');
            expect(propertyValues[0].nativeElement.innerText).toBe('property-1-value');
            expect(propertyValues[1].nativeElement.innerText).toBe('property-2-value');
        });
    });

    describe('Tooltip functionality', () => {

        let fixture: ComponentFixture<TestHostComponent>;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                declarations: [ DiagramTooltipComponent, TestHostComponent ]
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture  = TestBed.createComponent(TestHostComponent);

            fixture.detectChanges();
        });

        it('should NOT show the tooltip by default', () => {
            const tooltip = fixture.debugElement.query(By.css('.adf-diagram-tooltip.is-active'));

            expect(tooltip).toBeNull();
        });

        it('should show the tooltip on hovering the target element', () => {
            const tooltipTarget = fixture.debugElement.query(By.css('#diagram-element-id'));

            tooltipTarget.nativeElement.dispatchEvent(new MouseEvent('mouseenter'));

            let tooltip = fixture.debugElement.query(By.css('.adf-diagram-tooltip.is-active'));
            expect(tooltip).not.toBeNull();
        });

        it('should show the tooltip on touchend the target element', () => {
            const tooltipTarget = fixture.debugElement.query(By.css('#diagram-element-id'));

            tooltipTarget.nativeElement.dispatchEvent(new MouseEvent('touchend'));

            let tooltip = fixture.debugElement.query(By.css('.adf-diagram-tooltip.is-active'));
            expect(tooltip).not.toBeNull();
        });

        it('should hide the tooltip on leaving the target element', () => {
            const tooltipTarget = fixture.debugElement.query(By.css('#diagram-element-id'));

            tooltipTarget.nativeElement.dispatchEvent(new MouseEvent('mouseenter'));
            tooltipTarget.nativeElement.dispatchEvent(new MouseEvent('mouseleave'));

            let tooltip = fixture.debugElement.query(By.css('.adf-diagram-tooltip.is-active'));
            expect(tooltip).toBeNull();
        });

        it('should hide the tooltip on windows\'s scroll element', () => {
            const tooltipTarget = fixture.debugElement.query(By.css('#diagram-element-id'));

            tooltipTarget.nativeElement.dispatchEvent(new MouseEvent('mouseenter'));
            window.dispatchEvent(new CustomEvent('scroll'));

            let tooltip = fixture.debugElement.query(By.css('.adf-diagram-tooltip.is-active'));
            expect(tooltip).toBeNull();
        });

        it('should hide the tooltip on windows\'s touchstart element', () => {
            const tooltipTarget = fixture.debugElement.query(By.css('#diagram-element-id'));

            tooltipTarget.nativeElement.dispatchEvent(new MouseEvent('touchend'));
            window.dispatchEvent(new CustomEvent('touchstart'));

            let tooltip = fixture.debugElement.query(By.css('.adf-diagram-tooltip.is-active'));
            expect(tooltip).toBeNull();
        });
    });
});
